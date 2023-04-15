package com.ASWLogistic.repository;

import com.ASWLogistic.model.CardPayment;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.POD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, String> {
    List<CardPayment> findAll();

    CardPayment getCardPaymentById(String id);

    void deleteCardPaymentById(String id);
}
