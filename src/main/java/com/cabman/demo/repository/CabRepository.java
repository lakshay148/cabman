package com.cabman.demo.repository;

import com.cabman.demo.model.Cab;
import com.cabman.demo.model.CabStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CabRepository extends JpaRepository<Cab, UUID> {
    List<Cab> findAllByCityIdAndStatus(UUID cityId, CabStatus.Status status);
}
