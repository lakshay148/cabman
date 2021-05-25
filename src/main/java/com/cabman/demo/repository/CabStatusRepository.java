package com.cabman.demo.repository;

import com.cabman.demo.model.CabStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CabStatusRepository extends JpaRepository<CabStatus, UUID> {
}
