package com.cabman.demo.service;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CabService implements ICab{
    @Override
    public Cab registerCab(Cab cab) {
        return null;
    }

    @Override
    public List<Cab> getCabByCityAndStatus(UUID cityId, CabStatus.Status cabStatus) {
        return null;
    }
}
