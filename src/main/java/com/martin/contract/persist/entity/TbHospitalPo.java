package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.persist.base.BaseUserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    private Long createdUserId;

}