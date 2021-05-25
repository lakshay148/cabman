package com.cabman.demo.controller;

import com.cabman.demo.controller.request.AddCityRequest;
import com.cabman.demo.model.City;
import com.cabman.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping
    public ResponseEntity registerCity(@RequestBody AddCityRequest request){
        City city = new City();
        city.setName(request.getName());
        city.setPincode(request.getPincode());
        City addedCity = cityService.register(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCity);
    }

    @GetMapping
    public ResponseEntity getCities(){
        List<City> cityList = cityService.getRegisteredCities();
        return ResponseEntity.status(HttpStatus.FOUND).body(cityList);
    }
}
