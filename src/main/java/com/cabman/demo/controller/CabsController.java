package com.cabman.demo.controller;

import com.cabman.demo.controller.request.UpdateCabRequest;
import com.cabman.demo.model.Cab;
import com.cabman.demo.controller.request.AddCabRequest;
import com.cabman.demo.model.exception.BadRequestException;
import com.cabman.demo.service.CabService;
import com.cabman.demo.service.CabStatusService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/cabs")
public class CabsController {

    @GetMapping("/health")
    public String healthCheck(){
        return "Working Fine";
    }

    @Autowired
    CabService cabService;

    @Autowired
    CabStatusService cabStatusService;

    @PostMapping
    public ResponseEntity registerCab(@RequestBody AddCabRequest request){
        Cab cabToAdd = new Cab(request.getRegistrationNumber(), request.getCityId());
        Cab addedCab = cabService.registerCab(cabToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCab);
    }

    @PutMapping("{cabId}")
    public ResponseEntity updateCab(@RequestBody UpdateCabRequest request, @PathVariable String cabId){
        Cab cabToUpdate = new Cab();
        cabToUpdate.setId(UUID.fromString(cabId));
        cabToUpdate.setCityId(request.getCityId());
        cabToUpdate.setStatus(request.getStatus());
        Cab updatedCab = cabService.updateCab(cabToUpdate);

        //TODO Match if current status and next status are not same
        cabStatusService.change(updatedCab.getId(), updatedCab.getStatus() , request.getStatus());
        return ResponseEntity.status(HttpStatus.OK).body(updatedCab);
    }
}
