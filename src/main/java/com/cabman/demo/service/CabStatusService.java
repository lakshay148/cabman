package com.cabman.demo.service;

import com.cabman.demo.model.CabStatus;
import com.cabman.demo.repository.CabStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CabStatusService implements ICabStatus{

    @Autowired
    CabStatusRepository cabStatusRepository;

    @Override
    public CabStatus.Status change(UUID cabId, CabStatus.Status from, CabStatus.Status to) {
        CabStatus cabStatus = new CabStatus(cabId, from, to);
        CabStatus cabStatusSaved = cabStatusRepository.save(cabStatus);
        return cabStatusSaved.getNewStatus();
    }

    @Override
    public List<CabStatus.Status> getCabStatusHistory(UUID cabId, Date from, Date to) {
        return null;
    }
}
