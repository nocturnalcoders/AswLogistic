package com.ASWLogistic.dto;

import com.ASWLogistic.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ClaimDto {
    private Long id;

    private String containerCode;

    private String receivingNumber;

    private String claim;

    private String qty;

    private String price;

    private String rate;

    private String amount;

    private String remark;

    private String files;

    private User userId;
}
