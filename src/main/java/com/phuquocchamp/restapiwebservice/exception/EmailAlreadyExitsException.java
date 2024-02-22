package com.phuquocchamp.restapiwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExitsException extends RuntimeException {
    public EmailAlreadyExitsException(String message){
        super(message);
    }
}
