package com.bharath.journalapp.Exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ValidationErrorResponses>> handleConstraintViolation(ConstraintViolationException ex){
        List<ValidationErrorResponses> errors=ex.getConstraintViolations().stream().map(violation->new ValidationErrorResponses(violation.getPropertyPath().toString(),violation.getMessage())).toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //If u want to send the validation details in a header then use the code used in the restServices app where this class extends the
    //ResponseEntityExceptionHandler and then implements its methods, check it out

}
