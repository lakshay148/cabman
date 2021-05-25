package com.cabman.demo.service;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;

import java.util.List;
import java.util.UUID;

public interface ICab {
    Cab registerCab(Cab cab);
    Cab updateCab(Cab cab);
    List<Cab> getCabByCityAndStatus(UUID cityId, CabStatus.Status cabStatus);
}
