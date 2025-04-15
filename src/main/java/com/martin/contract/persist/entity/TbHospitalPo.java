package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.enums.ObjAuditStatus;
import com.martin.contract.persist.base.BaseUserPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_hospital")
public class TbHospitalPo extends BaseUserPo {

    private String fullName;

    private String address;

    private String province;

    private String city;

    private String area;

    @Builder.Default
    private ObjAuditStatus objAuditStatus = ObjAuditStatus.NEW;

}