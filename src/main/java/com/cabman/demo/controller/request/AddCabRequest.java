package com.cabman.demo.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddCabRequest {
    @Size(max = 15)
    String registrationNumber;
    UUID cityId;
}
