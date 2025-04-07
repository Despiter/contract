package com.martin.contract.controller;

import com.martin.contract.persist.entity.*;
import com.martin.contract.persist.mapper.SysUserMapper;
import com.martin.contract.persist.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController<SysUserPo, SysUserMapper, SysUserRepo> {
}
