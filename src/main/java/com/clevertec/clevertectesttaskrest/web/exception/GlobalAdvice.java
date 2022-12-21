package com.clevertec.clevertectesttaskrest.web.exception;

import com.clevertec.clevertectesttaskrest.service.exception.EntityAlreadyExistsException;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ErrorResponse handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(IncorrectRequestException.class)
    public ErrorResponse handleIncorrectRequestExceptionMessage(IncorrectRequestException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
