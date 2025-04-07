package com.martin.contract.controller;

import com.martin.contract.common.ResultData;
import com.martin.contract.common.TokenUtil;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.repo.SysUserRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.martin.contract.common.ReturnCodeEnum.RC300;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private SysUserRepo sysUserRepo;

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