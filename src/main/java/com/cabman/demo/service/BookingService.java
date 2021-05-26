package com.cabman.demo.service;

import com.cabman.demo.model.Booking;
import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;
import com.cabman.demo.model.exception.ResourceNotFoundException;
import com.cabman.demo.repository.BookingRepository;
import com.cabman.demo.repository.CabRepository;
import com.cabman.demo.repository.CabStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBooking{

    @Autowired
    CabService cabService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    CabStatusRepository statusRepository;

    @Override
    public Booking bookInCity( UUID cityId) {
        List<Cab> cabsInCity = cabService.getCabByCityAndStatus(cityId, CabStatus.Status.IDLE);
        if(cabsInCity == null || cabsInCity.size() == 0)
            throw new ResourceNotFoundException("No Cab in the city");

        Booking booking = new Booking();
        booking.setFromCity(cityId);
        UUID cabId = null;
        if(cabsInCity.size()>1){
            //TODO this can be optimized with having a idle from column in cab
            List<UUID> cabIds = getCabIds(cabsInCity);
            List<CabStatus> cabStatuses = statusRepository.findAllByCabIdInOrderByChangeTimeDesc(cabIds);
            HashMap<UUID, Date> cabIdleTimes = new HashMap<>();
            for(CabStatus status : cabStatuses){
                if(!cabIdleTimes.containsKey(status.getCabId())){
                    cabIdleTimes.put(status.getCabId(), status.getChangeTime());
                }
            }
            Date minDate = new Date();
            for(Map.Entry<UUID, Date> entry : cabIdleTimes.entrySet()){
                if(entry.getValue().before(minDate)){
                    minDate = entry.getValue();
                    cabId = entry.getKey();
                }
            }
        } else {
            cabId = cabsInCity.get(0).getId();
        }

        booking.setCabId(cabId);
        booking.setStatus(Booking.BookingStatus.BOOKED);

        //TODO a transaction here
        Optional<Cab> cabOptional = cabRepository.findById(cabId);
        Cab cab = cabOptional.get();
        cab.setStatus(CabStatus.Status.ON_TRIP);
        CabStatus cabStatus = new CabStatus();
        cabStatus.setCabId(cab.getId());
        cabStatus.setCurrentStatus(CabStatus.Status.IDLE);
        cabStatus.setNewStatus(CabStatus.Status.ON_TRIP);
        statusRepository.save(cabStatus);
        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;
    }

    @Override
    public List<Booking> getCabBookingFromAndTo(UUID cabId, Date from, Date to) {
        return null;
    }

    List<UUID> getCabIds(List<Cab> cabs){
        List<UUID> cabIds  = (List<UUID>) cabs.stream().map(cab -> cab.getId()).collect(Collectors.toList());
        return cabIds;
    }
}
