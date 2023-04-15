package com.ASWLogistic.exception.throwable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends Throwable{

    private String field;
    private String notFoundMessage;

}
