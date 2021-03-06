package com.cabman.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class Cab {

    public Cab(String registrationNumber, UUID cityId) {
        this.registrationNumber = registrationNumber;
        this.cityId = cityId;
    }

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;

    @Column(length = 15, unique = true)
    String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    CabStatus.Status status = CabStatus.Status.IDLE;

    @Type(type = "uuid-char")
    UUID cityId;

    @CreationTimestamp
    Date addedOn;
}
