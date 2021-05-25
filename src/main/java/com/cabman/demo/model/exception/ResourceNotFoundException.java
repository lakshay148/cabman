package com.cabman.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    String message;

    public ResourceNotFoundException(){
        super("Not Found");
        this.message = "Not Found";
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
