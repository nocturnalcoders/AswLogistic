package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CardPaymentDTO {
    private String cardName;
    private String cardTypeId;
    private String cardNumber;
    private String balance;
}
