package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PODDetailsDTO {
    private String podId;
    private String qcId;
    private String size;
    private String description;
    private String img;
}
