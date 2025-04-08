package com.martin.contract.controller;

import com.martin.contract.common.ResultData;
import com.martin.contract.common.TokenUtil;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.repo.SysUserRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.martin.contract.enums.ReturnCode.RC300;
import static com.martin.contract.enums.ReturnCode.RC301;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private SysUserRepo sysUserRepo;

    @PostMapping("/signup")
    public ResultData<String> signup(@RequestBody SysUserPo sysUser) {
        SysUserPo one = sysUserRepo.getOne(sysUser.getUserName(), sysUser.getPassword());
        if (one != null) {
            return ResultData.fail(RC301);
        }
        sysUserRepo.save(sysUser);
        String token = tokenUtil.generateToken(sysUser.getUserName());
        return ResultData.of(token);
    }

    @PostMapping("/login")
    public ResultData<String> login(@RequestParam String userName, @RequestParam String password) {
        SysUserPo one = sysUserRepo.getOne(userName, password);
        if (one != null) {
            // 2. 生成Token
            String token = tokenUtil.generateToken(one.getUserName());
            return ResultData.of(token);
        } else {
            return ResultData.fail(RC300);
        }
    }

    @PostMapping("/logout")
    public ResultData<Void> logout(@RequestHeader("UserName") String userName) {
        tokenUtil.deleteToken(userName);
        return ResultData.success();
    }
}