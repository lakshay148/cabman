package com.cabman.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class CabStatus {
    public enum Status{
        IDLE, ON_TRIP
    }

    public CabStatus(UUID cabId, Status currentStatus, Status newStatus) {
        this.cabId = cabId;
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @Type(type = "uuid-char")
    UUID cabId;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status" , length = 20)
    Status currentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", length = 20)
    Status newStatus;

    @CreationTimestamp
    Date changeTime;

}
