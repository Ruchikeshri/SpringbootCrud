package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value="${data.exception.message1}")
    private String message1;

    @ExceptionHandler(value=BlogAlreadyExistsException.class)
    public ResponseEntity<String> blogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException){
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    @Value(value="${data.exception.message2}")
    private String message2;

    @ExceptionHandler(value=BlogNotFoundException.class)
    public ResponseEntity<String> blogNotFoundException(BlogNotFoundException blogNotFoundException){
        return new ResponseEntity<String>(message2, HttpStatus.NOT_FOUND);
    }
}
