package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.enums.ObjRole;
import com.martin.contract.enums.ShareStatus;
import com.martin.contract.enums.ShareType;
import com.martin.contract.persist.base.BaseUserPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_obj_shared")
public class TbObjSharePo extends BaseUserPo {

    /**
     * 被共享用户ID
     */
    private Long sharedUserId;

    /**
     * 共享对象ID
     */
    private Long objId;

    /**
     * 共享对象角色
     */
    private ObjRole objRole;

    /**
     * 共享类型
     */
    private ShareType shareType;

    /**
     * 共享状态
     */
    private ShareStatus shareStatus;

}