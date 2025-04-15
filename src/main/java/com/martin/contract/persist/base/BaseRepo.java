package com.martin.contract.persist.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.martin.contract.common.PageRequest;

import java.util.List;

@DS("db_contract")
public abstract class BaseRepo<M extends BaseMapper<T>, T extends BasePo> extends ServiceImpl<M, T> {

    public List<T> page(PageRequest<T> pageRequest) {
        return baseMapper.selectPage(Page.of(pageRequest.getPageNo(), pageRequest.getPageSize()), Wrappers.lambdaQuery(pageRequest.getData())).getRecords();
    }

    public List<T> pageByIds(PageRequest<T> pageRequest, List<Long> ids) {
        return baseMapper.selectPage(Page.of(pageRequest.getPageNo(), pageRequest.getPageSize()),
                Wrappers.lambdaQuery(pageRequest.getData()).in(BasePo::getId, ids)).getRecords();
    }

}