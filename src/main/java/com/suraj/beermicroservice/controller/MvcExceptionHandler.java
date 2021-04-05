package com.suraj.beermicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        List<String> errorList = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(error-> errorList.add(error.toString()));

        return new ResponseEntity<List>(errorList, HttpStatus.BAD_REQUEST);
    }
}
