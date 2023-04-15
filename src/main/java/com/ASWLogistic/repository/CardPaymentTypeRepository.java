package com.ASWLogistic.repository;

import com.ASWLogistic.model.CardPayment;
import com.ASWLogistic.model.CardPaymentType;
import com.ASWLogistic.model.ExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardPaymentTypeRepository extends JpaRepository<CardPaymentType, String> {
    List<CardPaymentType> findAll();

    CardPaymentType getCardPaymentTypeById(String id);

    void deleteCardPaymentTypeById(String id);
}
