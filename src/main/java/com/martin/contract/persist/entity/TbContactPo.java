package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.enums.ObjRole;
import com.martin.contract.persist.base.BaseUserPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_contact")
public class TbContactPo extends BaseUserPo {

    private String name;

    private String phone;

    private String email;

    private Long refId;

    /**
     * 所属对象角色
     */
    private ObjRole objRole;

}