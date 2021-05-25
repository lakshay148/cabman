package com.cabman.demo.service;

import com.cabman.demo.model.City;

import java.util.List;
import java.util.UUID;

public interface ICity {
    City register(City city);
    City unRegister(UUID cityId);
    List<City> getRegisteredCities();
}
