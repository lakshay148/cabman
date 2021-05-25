package com.cabman.demo.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnRegisterRequest {
    String action = "unregister";
}
