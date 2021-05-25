package com.cabman.demo.service;

import com.cabman.demo.model.CabStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CabStatusService implements ICabStatus{
    @Override
    public CabStatus.Status change(UUID cabId, CabStatus.Status from, CabStatus.Status to) {
        return null;
    }

    @Override
    public List<CabStatus.Status> getCabStatusHistory(UUID cabId, Date from, Date to) {
        return null;
    }
}
