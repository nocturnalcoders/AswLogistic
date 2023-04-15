package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;

    private String customerName;

    private String address;

    private String province;

    private String accountNumber;

    private String marking;
}
