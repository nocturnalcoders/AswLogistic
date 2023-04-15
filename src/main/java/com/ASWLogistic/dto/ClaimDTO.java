package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Accessors(chain = true)
public class ClaimDTO {
    private String containerCode;
    private String receivingNumber;
    private String claim;
    private String qty;
    private String price;
    private String rate;
    private String amount;
    private String remark;
    private MultipartFile files;
}
