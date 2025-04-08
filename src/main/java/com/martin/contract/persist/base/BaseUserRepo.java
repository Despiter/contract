package com.martin.contract.persist.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

@DS("db_contract")
public abstract class BaseUserRepo<M extends BaseMapper<T>, T extends BaseUserPo> extends ServiceImpl<M, T> {

    public List<T> listByIds(List<Long> organizationIds, T t) {
        return list(Wrappers.lambdaQuery(t).in(T::getId, organizationIds));
    }

}