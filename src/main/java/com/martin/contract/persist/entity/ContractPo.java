package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.persist.base.BasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract")
public class ContractPo extends BasePo {

    private String contractNo;

    private Long organizationId;

    private BusinessMode businessMode;

    private Status status;

    private BigDecimal amount;

    private Long createdBy;

    @Getter
    @AllArgsConstructor
    public enum BusinessMode {
        DIRECT("直接签约"),
        PARTNER("合作签约");

        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        DRAFT("草稿"),
        PROJECT_APPLY("项目申请"),
        BID_APPLY("招标申请"),
        SIGNED("已签约"),
        COMPLETED("已完成");

        private final String desc;
    }
}