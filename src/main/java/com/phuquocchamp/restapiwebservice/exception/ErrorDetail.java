package com.phuquocchamp.restapiwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorCode;
}
