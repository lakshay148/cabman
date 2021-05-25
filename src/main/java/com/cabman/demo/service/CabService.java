package com.cabman.demo.service;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;
import com.cabman.demo.model.City;
import com.cabman.demo.model.exception.ResourceNotFoundException;
import com.cabman.demo.repository.CabRepository;
import com.cabman.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Cab registerCab(Cab cab) {
        Cab savedCab = cabRepository.save(cab);
        return savedCab;
    }

    @Override
    public Cab updateCab(Cab cab) {
        Optional<Cab> savedCab = cabRepository.findById(cab.getId());
        if(savedCab.isEmpty())
            throw new ResourceNotFoundException();
        Cab cabToUpdate = savedCab.get();
        if(cab.getStatus() != null) cabToUpdate.setStatus(cab.getStatus());
        if(cab.getCityId() != null){
            Optional<City> savedCity =  cityRepository.findById(cab.getCityId());
            if(savedCity.isEmpty())
                throw new ResourceNotFoundException();
            cabToUpdate.setCityId(cab.getCityId());
        }
        cabRepository.save(cabToUpdate);
        return cabToUpdate;
    }

    @Override
    public List<Cab> getCabByCityAndStatus(UUID cityId, CabStatus.Status cabStatus) {
        List<Cab> idleCabs = cabRepository.findAllByCityIdAndStatus(cityId, cabStatus);
        return idleCabs;
    }
}
