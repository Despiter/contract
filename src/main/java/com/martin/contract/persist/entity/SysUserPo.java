package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.enums.UserRole;
import com.martin.contract.persist.base.BasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUserPo extends BasePo {

    private String userName;

    private String password;

    private String realName;

    private UserRole userRole;

    private Long parentId;

}
