package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.CardPaymentDTO;
import com.ASWLogistic.dto.CardPaymentTypeDTO;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.CardPayment;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Payment;
import com.ASWLogistic.repository.CardPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardPaymentService {

    private final CardPaymentRepository cardPaymentRepository;
    public List<CardPayment> findAll() {
        return cardPaymentRepository.findAll();
    }

    public CardPayment getCardPaymentById(String id) {
        return cardPaymentRepository.getCardPaymentById(id);
    }

    public CardPayment addCardPayment(CardPaymentDTO cardPaymentDTO) {
        CardPayment cardPayment = new CardPayment();
        cardPayment.setBalance(cardPaymentDTO.getBalance());
        cardPayment.setCardTypeId(cardPaymentDTO.getCardTypeId());
        cardPayment.setCardNumber(cardPaymentDTO.getCardNumber());
        cardPayment.setCardName(cardPaymentDTO.getCardName());
        return cardPaymentRepository.save(cardPayment);
    }
    public CardPayment updateCardPaymentById(String id, CardPaymentDTO cardPaymentDTO) throws NotFoundException {
        CardPayment cardPayment = cardPaymentRepository.getCardPaymentById(id);
        if (cardPayment == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        cardPayment.setCardNumber(cardPaymentDTO.getCardNumber());
        cardPayment.setCardTypeId(cardPaymentDTO.getCardTypeId());
        cardPayment.setCardName(cardPaymentDTO.getCardName());
        cardPayment.setBalance(cardPaymentDTO.getBalance());
        return cardPaymentRepository.save(cardPayment);
    }

    public void deleteCardPaymentById(String id) {
        cardPaymentRepository.deleteCardPaymentById(id);
    }

}
