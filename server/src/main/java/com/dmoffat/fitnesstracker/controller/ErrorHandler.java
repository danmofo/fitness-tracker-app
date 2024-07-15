package com.dmoffat.fitnesstracker.controller;

import com.dmoffat.fitnesstracker.model.response.ValidationError;
import com.dmoffat.fitnesstracker.model.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ValidationErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        var validationErrors = ex.getBindingResult().getAllErrors().stream()
            .map(error ->
                new ValidationError(((FieldError) error).getField(), error.getDefaultMessage())
            )
            .toList();

        return new ValidationErrorResponse(validationErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        return Map.of("error", "true");
    }
}
