package com.cabman.demo.service;

import com.cabman.demo.model.City;
import com.cabman.demo.model.exception.BadRequestException;
import com.cabman.demo.model.exception.ResourceNotFoundException;
import com.cabman.demo.repository.CityRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityService implements ICity{

    @Autowired
    CityRepository cityRepository;

    @Override
    public City register(City city) {
        City savedCity;
        try {
             savedCity = cityRepository.save(city);
        } catch (Exception exception){
            throw new BadRequestException("City already exists");
        }

        return savedCity;
    }

    @Override
    public City unRegister(UUID cityId) {
        Optional<City> savedCity =  cityRepository.findById(cityId);
        if(savedCity.isEmpty())
            throw new ResourceNotFoundException();
        City cityToUpdate = savedCity.get();
        cityToUpdate.setActive(false);
        cityRepository.save(cityToUpdate);
        return cityToUpdate;
    }

    @Override
    public List<City> getRegisteredCities() {
        List<City> activeCities = cityRepository.findAllByActive(true);
        return activeCities;
    }
}
