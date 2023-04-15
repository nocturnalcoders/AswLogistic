package com.ASWLogistic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExtraFeeDTO {
    private String description;
    private String nominal;
    private String notes;
}
