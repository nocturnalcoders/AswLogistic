package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
public class StuffingDTO {
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
}
