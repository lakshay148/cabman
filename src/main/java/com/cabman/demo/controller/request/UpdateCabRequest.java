package com.cabman.demo.controller.request;

import com.cabman.demo.model.CabStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdateCabRequest {
    UUID cityId;
    CabStatus.Status status;
}
