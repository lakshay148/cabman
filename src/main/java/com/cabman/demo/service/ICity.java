package com.cabman.demo.service;

import com.cabman.demo.model.City;

import java.util.List;
import java.util.UUID;

public interface ICity {
    City register(City city);
    City activate(UUID cityId);
    City deactivate(UUID cityId);
    List<City> getRegisteredCities();
}
