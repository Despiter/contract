package com.martin.contract.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.martin.contract.common.PageRequest;
import com.martin.contract.common.ResultData;
import com.martin.contract.persist.base.BasePo;
import com.martin.contract.persist.base.BaseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
public abstract class BaseController<T extends BasePo, M extends BaseMapper<T>, R extends BaseRepo<M, T>> {

    protected abstract R getRepo();

    @PostMapping("/insert")
    public ResultData<Boolean> insert(@RequestBody T t) {
        return ResultData.of(getRepo().save(t));
    }

    @DeleteMapping("/delete/{id}")
    public ResultData<Boolean> delete(@PathVariable("id") long id) {
        return ResultData.of(getRepo().removeById(id));
    }

    @PutMapping("/update")
    public ResultData<Boolean> update(@RequestBody T t) {
        return ResultData.of(getRepo().updateById(t));
    }

    @GetMapping("/get/{id}")
    public ResultData<T> get(@PathVariable("id") long id) {
        return ResultData.of(getRepo().getById(id));
    }

    @GetMapping("/page")
    public ResultData<List<T>> page(@RequestBody PageRequest<T> pageRequest) {
        return ResultData.of(getRepo().page(pageRequest));
    }

}
