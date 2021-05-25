package com.cabman.demo.controller;

import com.cabman.demo.controller.request.AddCityRequest;
import com.cabman.demo.controller.request.UnRegisterRequest;
import com.cabman.demo.model.City;
import com.cabman.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.status(HttpStatus.OK).body(cityList);
    }

    @PutMapping("/{cityId}")
    ResponseEntity unRegister(@RequestBody UnRegisterRequest request, @PathVariable String cityId){
        if(request.getAction().equalsIgnoreCase("activate")){
            City city = cityService.activate(UUID.fromString(cityId));
            return ResponseEntity.status(HttpStatus.OK).body(city);
        } else if (request.getAction().equalsIgnoreCase("deactivate")){
            City city = cityService.deactivate(UUID.fromString(cityId));
            return ResponseEntity.status(HttpStatus.OK).body(city);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
