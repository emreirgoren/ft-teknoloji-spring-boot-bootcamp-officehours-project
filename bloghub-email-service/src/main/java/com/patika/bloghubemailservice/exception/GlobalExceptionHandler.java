package com.patika.bloghubemailservice.exception;

import com.patika.bloghubemailservice.dto.response.GenericResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public GenericResponse handleEmailNotFoundException(EmailNotFoundException emailNotFoundException) {
        return GenericResponse.failed(emailNotFoundException.getMessage());
    }
}