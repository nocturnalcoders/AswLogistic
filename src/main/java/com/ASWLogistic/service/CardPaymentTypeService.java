package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.CardPaymentTypeDTO;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.CardPaymentType;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.repository.CardPaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardPaymentTypeService {

    private final CardPaymentTypeRepository cardPaymentTypeRepository;


    public CardPaymentType addCardPaymentType(CardPaymentTypeDTO cardPaymentTypeDTO) {
        CardPaymentType cardPaymentType = new CardPaymentType();
        cardPaymentType.setCardNameType(cardPaymentTypeDTO.getCardNameType());
        return cardPaymentTypeRepository.save(cardPaymentType);
    }

    public CardPaymentType getCardPaymentTypeById(String id) {
        return cardPaymentTypeRepository.getCardPaymentTypeById(id);
    }

    public CardPaymentType updateCardPaymentTypeId(String id, CardPaymentTypeDTO cardPaymentTypeDTO) throws NotFoundException {
        CardPaymentType cardPaymentType = cardPaymentTypeRepository.getCardPaymentTypeById(id);
        if (cardPaymentType == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        cardPaymentType.setCardNameType(cardPaymentTypeDTO.getCardNameType());
        return cardPaymentTypeRepository.save(cardPaymentType);
    }
    public void deleteCardPaymentTypeById(String id) {
        cardPaymentTypeRepository.deleteCardPaymentTypeById(id);
    }

    public List<CardPaymentType> findAll() {
        return cardPaymentTypeRepository.findAll();
    }



}
