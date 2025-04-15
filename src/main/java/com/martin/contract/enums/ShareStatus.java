package com.martin.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShareStatus {
    NEW("新建"),
    FAILED("审核失败"),
    SUCCESS("审核通过"),
    ;

    private final String desc;
}
