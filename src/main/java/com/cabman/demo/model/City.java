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
public class City {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;

    @Column(length = 120)
    String name;

    @Column(unique = true)
    int pincode;

    @Column
    boolean active = true;

    @CreationTimestamp
    Date addedOn;
}
