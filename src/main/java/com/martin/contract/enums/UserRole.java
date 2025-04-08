package com.martin.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    SALES("销售"),
    MANAGER("高管"),
    ADMIN("管理员");

    private final String desc;
}
