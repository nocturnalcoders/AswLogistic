package com.ASWLogistic.dto;

import com.ASWLogistic.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class PODDTO {
    private String containerCode;

    private String receivingNumber;

    private String deliveryType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date receivingDate;

    private String driverId;

    private String status;

    private String files;

    private String extrafeeId;

    private String localReceipt;

    private User userId;
}
