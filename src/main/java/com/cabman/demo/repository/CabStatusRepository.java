package com.cabman.demo.repository;

import com.cabman.demo.model.CabStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CabStatusRepository extends JpaRepository<CabStatus, UUID> {
//    CabStatus findTopByCityIdAndNewStatusOrderByChangeTimeAsc(UUID cityId, CabStatus.Status status);
//    @Query("select * from cab_status where cab_id in ")
    List<CabStatus> findAllByCabIdInOrderByChangeTimeDesc(List<UUID> cabIds);
}
