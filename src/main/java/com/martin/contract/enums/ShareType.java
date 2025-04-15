package com.martin.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShareType {
    OBJ("客户对象"),
    CONTACT("联系人"),
    CONTRACT("合同"),
    ;

    private final String desc;
}
