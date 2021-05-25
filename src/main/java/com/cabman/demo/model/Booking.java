package com.cabman.demo.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Booking {

    public enum BookingStatus {
        BOOKED, WAITING, REJECTED
    }

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;

    @Type(type = "uuid-char")
    UUID cabId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    BookingStatus status;

    @CreationTimestamp
    Date createdOn;
}
