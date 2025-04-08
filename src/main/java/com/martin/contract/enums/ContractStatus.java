package com.martin.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractStatus {
    DRAFT("草稿"),
    PROJECT_APPLY("项目申请"),
    BID_APPLY("招标申请"),
    SIGNED("已签约"),
    COMPLETED("已完成");

    private final String desc;
}
