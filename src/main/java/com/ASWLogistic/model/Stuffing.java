package com.ASWLogistic.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "stuffing")
@Data
@NoArgsConstructor
public class Stuffing implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String containerCode;
    private String receivingNumber;
    private String containerNumber;
    private Timestamp stuffingDate;
    private Timestamp departureDate;
    private Timestamp arrivalDate;
    private Timestamp etaDate;
    private String status;
    private String files;
    private String stuffingSummary;
    private String extrafeeId;
    private String userId;
}
