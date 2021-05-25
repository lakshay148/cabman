package com.cabman.demo.service;

import com.cabman.demo.model.Booking;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IBooking {
    Booking bookInCity(UUID cityId);
    List<Booking> getCabBookingFromAndTo(UUID cabId, Date from, Date to);
}
