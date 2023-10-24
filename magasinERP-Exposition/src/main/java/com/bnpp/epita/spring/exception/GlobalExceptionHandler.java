package com.bnpp.epita.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<MessageExceptionDto> handleClientByIdNotFound (EntityNotFoundException exception){
        MessageExceptionDto errorMessage=new MessageExceptionDto("NOT_FOUND", exception.getMessage(), LocalDate.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<MessageExceptionDto> handleClientByIdNotFound (EntityNotFoundException exception){
        MessageExceptionDto errorMessage=new MessageExceptionDto("NOT_FOUND", exception.getMessage(), LocalDate.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }

     */
}
