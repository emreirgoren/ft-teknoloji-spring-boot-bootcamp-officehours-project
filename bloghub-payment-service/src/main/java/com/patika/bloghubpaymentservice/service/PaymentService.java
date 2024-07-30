package com.patika.bloghubpaymentservice.service;

import com.patika.bloghubpaymentservice.converter.PaymentConverter;
import com.patika.bloghubpaymentservice.dto.request.PaymentRequest;
import com.patika.bloghubpaymentservice.dto.response.PaymentResponse;
import com.patika.bloghubpaymentservice.model.PaymentStatus;
import com.patika.bloghubpaymentservice.repository.PaymentRepository;
import com.patika.bloghubpaymentservice.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponse createPayment(PaymentRequest request) {

        Payment payment = PaymentConverter.toEntity(request, PaymentStatus.PAID);

        paymentRepository.save(payment);

        return PaymentConverter.toResponse(payment);
    }

    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return PaymentConverter.toResponse(payments);
    }
}
