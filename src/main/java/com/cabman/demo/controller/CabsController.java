package com.cabman.demo.controller;

import com.cabman.demo.controller.request.UpdateCabRequest;
import com.cabman.demo.model.Cab;
import com.cabman.demo.controller.request.AddCabRequest;
import com.cabman.demo.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/cabs/")
public class CabsController {

    @GetMapping("/health")
    public String healthCheck(){
        return "Working Fine";
    }

    @Autowired
    CabService cabService;

    @PostMapping
    public ResponseEntity registerCab(AddCabRequest request){
        Cab cabToAdd = new Cab(request.getRegistrationNumber(), request.getCityId());
        Cab cab = cabService.registerCab(cabToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(cab);
    }

    @PutMapping("{cabId}")
    public ResponseEntity updateCab(UpdateCabRequest request, @PathVariable String cabId){
        Cab cabToUpdate = new Cab();
        cabToUpdate.setId(UUID.fromString(cabId));
        cabToUpdate.setCityId(request.getCityId());
        cabToUpdate.setStatus(request.getStatus());
        Cab updatedCab = cabService.updateCab(cabToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCab);
    }
}
