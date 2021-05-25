package com.cabman.demo.service;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;
import com.cabman.demo.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CabService implements ICab{

    @Autowired
    CabRepository cabRepository;

    @Override
    public Cab registerCab(Cab cab) {
        return null;
    }

    @Override
    public Cab updateCab(Cab cab) {
        return null;
    }

    @Override
    public List<Cab> getCabByCityAndStatus(UUID cityId, CabStatus.Status cabStatus) {
        return null;
    }
}
