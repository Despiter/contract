package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.enums.CustomerRole;
import com.martin.contract.persist.base.BaseUserPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_shared_organization")
public class TbShareOrganizationPo extends BaseUserPo {

    private Long shareUserId;

    private Long customerId;

    private CustomerRole customerRole;

}