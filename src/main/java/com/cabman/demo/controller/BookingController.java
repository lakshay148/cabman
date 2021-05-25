package com.cabman.demo.controller;

import com.cabman.demo.controller.request.BookCabRequest;
import com.cabman.demo.model.Booking;
import com.cabman.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity book(@RequestBody BookCabRequest request){
        Booking booking = bookingService.bookInCity(UUID.fromString(request.getCityId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
}
