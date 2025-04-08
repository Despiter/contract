package com.martin.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMode {
    DIRECT("直接签约"),
    PARTNER("合作签约");

    private final String desc;
}
