package com.cabman.demo.service;

import com.cabman.demo.model.Booking;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService implements IBooking{

    @Override
    public Booking bookInCity( UUID cityId) {
        return null;
    }

    @Override
    public List<Booking> getCabBookingFromAndTo(UUID cabId, Date from, Date to) {
        return null;
    }
}
