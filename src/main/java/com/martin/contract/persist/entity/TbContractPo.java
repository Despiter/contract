package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.enums.BusinessMode;
import com.martin.contract.enums.ContractStatus;
import com.martin.contract.persist.base.BaseUserPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_contract")
public class TbContractPo extends BaseUserPo {

    private String contractNo;

    private Long objId;

    private BusinessMode businessMode;

    private ContractStatus status;

    private BigDecimal amount;

}