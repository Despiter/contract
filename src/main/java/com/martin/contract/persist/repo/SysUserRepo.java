package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.martin.contract.persist.base.BaseRepo;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.mapper.SysUserMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class SysUserRepo extends BaseRepo<SysUserMapper, SysUserPo> {

    public SysUserPo getOne(String userName, String password) {
        return getOne(Wrappers.<SysUserPo>lambdaQuery().eq(SysUserPo::getUserName, userName));
    }

}
