package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CardPaymentTypeDTO {
    private String cardNameType;
}
