package com.cabman.demo.service;

import com.cabman.demo.model.City;
import com.cabman.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService implements ICity{

    @Autowired
    CityRepository cityRepository;

    @Override
    public City register(City city) {
        City savedCity = cityRepository.save(city);
        return savedCity;
    }

    @Override
    public City unRegister(UUID cityId) {
        return null;
    }

    @Override
    public List<City> getRegisteredCities() {
        return null;
    }
}
