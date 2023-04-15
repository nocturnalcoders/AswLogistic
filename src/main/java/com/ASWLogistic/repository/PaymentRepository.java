package com.ASWLogistic.repository;

import com.ASWLogistic.model.CardPayment;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findAll();

    Payment getPaymentById(String id);

    void deletePaymentById(String id);
}
