package com.ASWLogistic.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "quality_control")
@Data
public class QualityControl {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String receivingNumber;
    private UUID customerId;
    private String chinaReceipt;
    private Timestamp receivingDate;
    private UUID stuffingId;
    private String deliveryType;
    private String category;
    private String type;
    private String qc;
    private String cbm;
    private String kg;
    private String t;
    private String l;
    private String p;
    private String packaging;
    private String chargeFee;
    private String extrafeeId;
    private String files;
    private String notes;
    private String status;
    private String qr;
}
