package com.patika.bloghubpaymentservice.converter;

import com.patika.bloghubpaymentservice.dto.request.PaymentRequest;
import com.patika.bloghubpaymentservice.dto.response.PaymentResponse;
import com.patika.bloghubpaymentservice.model.Payment;
import com.patika.bloghubpaymentservice.model.PaymentStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentConverter {


    public static Payment toEntity(PaymentRequest request, PaymentStatus paymentStatus) {
        return new Payment(request.getAmount(), LocalDateTime.now(), paymentStatus, request.getEmail());
    }

    public static PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .createdDateTime(payment.getCreatedDateTime())
                .email(payment.getEmail())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public static List<PaymentResponse> toResponse(List<Payment> payments) {
        return payments.stream()
                .map(PaymentConverter::toResponse)
                .toList();
    }
}
