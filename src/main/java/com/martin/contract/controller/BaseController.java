package com.martin.contract.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.martin.contract.common.Request;
import com.martin.contract.common.ResultData;
import com.martin.contract.persist.base.BasePo;
import com.martin.contract.persist.base.BaseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public abstract class BaseController<T extends BasePo, M extends BaseMapper<T>, R extends BaseRepo<M, T>> {

    @Resource
    protected R r;

    @PostMapping("/insert")
    public ResultData<Boolean> insert(@RequestBody T t) {
        return ResultData.of(r.save(t));
    }

    @DeleteMapping("/delete/{id}")
    public ResultData<Boolean> delete(@PathVariable("id") long id) {
        return ResultData.of(r.removeById(id));
    }

    @PutMapping("/update")
    public ResultData<Boolean> update(@RequestBody T t) {
        return ResultData.of(r.updateById(t));
    }

    @GetMapping("/page")
    public ResultData<List<T>> page(@RequestBody Request<T> request) {
        return ResultData.of(r.page(request));
    }

}
