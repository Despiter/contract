package com.martin.contract.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.martin.contract.common.Request;
import com.martin.contract.common.RestResponse;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.repo.SysUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController("/user")
public class SysUserController {

    @Resource
    private SysUserRepo sysUserRepo;

    @PostMapping("/insert")
    public RestResponse<Void> insert(@RequestBody SysUserPo po) {
        sysUserRepo.save(po);
        return RestResponse.success();
    }

    @DeleteMapping("/delete/{id}")
    public RestResponse<Boolean> delete(@PathVariable("id") long id) {
        return RestResponse.of(sysUserRepo.removeById(id));
    }

    @PutMapping("/update")
    public RestResponse<Boolean> update(@RequestBody SysUserPo po) {
        return RestResponse.of(sysUserRepo.updateById(po));
    }

    @GetMapping("/page")
    public RestResponse<List<SysUserPo>> page(@RequestBody Request<SysUserPo> request) {
        return RestResponse.of(sysUserRepo.page(request));
    }

}
