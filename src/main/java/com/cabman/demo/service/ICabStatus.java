package com.cabman.demo.service;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;

import java.util.UUID;

public interface ICabStatus {
    CabStatus.Status change(UUID cabId, CabStatus.Status from , CabStatus.Status to);
}
