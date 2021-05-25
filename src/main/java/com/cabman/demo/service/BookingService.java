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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            throw new ResourceNotFoundException("Cab Not Found");

        Booking booking = new Booking();
        booking.setFromCity(cityId);
        booking.setCabId(cabsInCity.get(0).getId());
        booking.setStatus(Booking.BookingStatus.BOOKED);

        //TODO a transaction here
        Optional<Cab> cabOptional = cabRepository.findById(cabsInCity.get(0).getId());
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
}
