package com.martin.contract.persist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.martin.contract.persist.base.BasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("organization")
public class OrganizationPo extends BasePo {

    private String fullName;

    private String address;

    private Role role;

    private String province;

    private String city;

    private String area;

    private Long createdBy;

    @Getter
    @AllArgsConstructor
    public enum Role {
        HOSPITAL("医院"),
        PARTNER("合作方");

        private final String desc;
    }
}