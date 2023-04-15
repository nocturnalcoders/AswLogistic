package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Accessors(chain = true)
public class PaymentDTO {
    private String invoiceId;

    private String nominal;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    private String type;

    private String accountNumber;

    private String note;

    private String img;
    private String status;

    private String userId;

}
