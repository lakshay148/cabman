package com.cabman.demo.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@NoArgsConstructor
public class AddCityRequest {
    String name;
    @Max(value = 999999)
    int pincode;
}
