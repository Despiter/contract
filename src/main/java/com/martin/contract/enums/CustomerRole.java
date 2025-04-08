package com.martin.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerRole {
    HOSPITAL("医院"),
    PARTNER("合作方");

    private final String desc;
}
