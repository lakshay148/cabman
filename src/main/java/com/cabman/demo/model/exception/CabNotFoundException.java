package com.cabman.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "No Cab Found in the city")
public class CabNotFoundException extends RuntimeException{
}
