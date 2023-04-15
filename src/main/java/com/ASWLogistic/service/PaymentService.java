package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.dto.PaymentDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Payment;
import com.ASWLogistic.repository.ExtraFeeRepository;
import com.ASWLogistic.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment addPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setInvoiceId(paymentDTO.getInvoiceId());
        payment.setNominal(paymentDTO.getNominal());
        payment.setDate(paymentDTO.getDate());
        payment.setType(paymentDTO.getType());
        payment.setAccountNumber(paymentDTO.getAccountNumber());
        payment.setNote(paymentDTO.getNote());
        payment.setImg(paymentDTO.getImg());
        payment.setStatus(paymentDTO.getStatus());
        payment.setUserId(paymentDTO.getUserId());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(String id) {
        return paymentRepository.getPaymentById(id);
    }

    public Payment updatePaymentById(String id, PaymentDTO paymentDTO) throws NotFoundException {
        Payment payment = paymentRepository.getPaymentById(id);
        if (payment == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        payment.setInvoiceId(paymentDTO.getInvoiceId());
        payment.setNominal(paymentDTO.getNominal());
        payment.setDate(paymentDTO.getDate());
        payment.setType(paymentDTO.getType());
        payment.setAccountNumber(paymentDTO.getAccountNumber());
        payment.setNote(paymentDTO.getNote());
        payment.setImg(paymentDTO.getImg());
        payment.setStatus(paymentDTO.getStatus());
        payment.setUserId(paymentDTO.getUserId());
        return paymentRepository.save(payment);
    }
    public void deletePaymentById(String id) {
        paymentRepository.deletePaymentById(id);
    }



}
