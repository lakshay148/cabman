package com.cabman.demo.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AddCabRequest {
    String registrationNumber;
    UUID cityId;
}
