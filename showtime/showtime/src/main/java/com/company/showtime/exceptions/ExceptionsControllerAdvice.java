package com.company.showtime.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *          -- CURRENTLY UNDER CONSTRUCTION --
 *          This is supposed to be an exception
 *              handler for the frontend
 */

@ControllerAdvice
public class ExceptionsControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<String> handleCustomException(CustomException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
