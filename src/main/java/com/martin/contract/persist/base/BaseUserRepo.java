package com.martin.contract.persist.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.List;

@DS("db_contract")
public abstract class BaseUserRepo<M extends BaseMapper<T>, T extends BaseUserPo> extends BaseRepo<M, T> {

    public List<T> listByUserIds(List<Long> userIds, T t) {
        return list(Wrappers.lambdaQuery(t).in(T::getCreatedUserId, userIds));
    }

    /**
     * 找出所有非我本人创建的
     */
    public List<T> listNotMy(Long createUserId, T t) {
        return list(Wrappers.lambdaQuery(t).ne(T::getCreatedUserId, createUserId));
    }

}