package com.cabman.demo.service;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;
import com.cabman.demo.model.City;
import com.cabman.demo.model.exception.BadRequestException;
import com.cabman.demo.model.exception.ResourceNotFoundException;
import com.cabman.demo.repository.CabRepository;
import com.cabman.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Service
public class CabService implements ICab{

    @Autowired
    CabRepository cabRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CabStatusService cabStatusService;

    @Override
    public Cab registerCab(Cab cab) {
        Optional<City> savedCity =  cityRepository.findById(cab.getCityId());
        if(savedCity.isEmpty())
            throw new ResourceNotFoundException("City not found");
        Cab savedCab;
        try
        {
            savedCab = cabRepository.save(cab);
        } catch (DataIntegrityViolationException exception){
            throw new BadRequestException("Cab already registerd");
        }
        cabStatusService.change(cab.getId(), null, CabStatus.Status.IDLE);
        return savedCab;
    }

    @Override
    public Cab updateCab(Cab cab) {
        Optional<Cab> savedCab = cabRepository.findById(cab.getId());
        if(savedCab.isEmpty())
            throw new ResourceNotFoundException("Cab not found");
        Cab cabToUpdate = savedCab.get();
        if(cab.getStatus() != null) {
            if(cab.getStatus() == cabToUpdate.getStatus())
                throw new BadRequestException("status is already "+ cab.getStatus());

            //TODO can also make independent requests to avoid it to make a transaction
            cabStatusService.change(cab.getId(), cabToUpdate.getStatus() , cab.getStatus());
            cabToUpdate.setStatus(cab.getStatus());
        }
        if(cab.getCityId() != null){
            Optional<City> savedCity =  cityRepository.findById(cab.getCityId());
            if(savedCity.isEmpty())
                throw new ResourceNotFoundException("City not found");
            cabToUpdate.setCityId(cab.getCityId());
        }
        cabRepository.save(cabToUpdate);
        return cabToUpdate;
    }

    @Override
    public List<Cab> getCabByCityAndStatus(UUID cityId, CabStatus.Status cabStatus) {
        Optional<City> savedCity =  cityRepository.findById(cityId);
        if(savedCity.isEmpty())
            throw new ResourceNotFoundException("City not found");
        List<Cab> idleCabs = cabRepository.findAllByCityIdAndStatus(cityId, cabStatus);
        return idleCabs;
    }
}
