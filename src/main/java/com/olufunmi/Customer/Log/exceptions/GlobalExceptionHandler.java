package com.olufunmi.Customer.Log.exceptions;

import com.olufunmi.Customer.Log.dtos.responses.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> handleEmailAlreadyExistException(EmailAlreadyExistException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .statusCode(406)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?> handleEmailNotFoundException(EmailNotFoundException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .statusCode(404)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
