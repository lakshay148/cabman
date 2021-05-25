package com.cabman.demo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.UUID;

@Entity
public class CabStatus {
    public enum Status{
        IDLE, ON_TRIP
    }

    @Type(type = "uuid-char")
    UUID cabId;

    @Column(name = "current_status" , length = 20)
    Status currentStatus;

    @Column(name = "new_status", length = 20)
    Status newStatus;

    @CreationTimestamp
    Date changeTime;

}
