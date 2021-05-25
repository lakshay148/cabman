package com.cabman.demo.service;

import com.cabman.demo.model.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService implements ICity{
    @Override
    public City register(City city) {
        return null;
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
