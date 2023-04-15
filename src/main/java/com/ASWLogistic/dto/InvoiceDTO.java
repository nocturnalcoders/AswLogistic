package com.ASWLogistic.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InvoiceDTO {
    private String customerId;

    private String receivingNumber;

    private String podId;

    private String discountFinal;

    private String deliveryFee;

    private String total;

    private String balance;

    private String status;
}
