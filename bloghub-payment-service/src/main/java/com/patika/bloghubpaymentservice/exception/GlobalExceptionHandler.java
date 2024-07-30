package com.patika.bloghubpaymentservice.exception;

import com.patika.bloghubpaymentservice.dto.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    public GenericResponse handlePaymentException(PaymentException paymentException){
        return GenericResponse.failed(paymentException.getMessage());
    }

}
