package com.martin.contract.controller;

import com.martin.contract.persist.entity.TbContactPo;
import com.martin.contract.persist.mapper.TbContactMapper;
import com.martin.contract.persist.repo.TbContactRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController extends BaseController<TbContactPo, TbContactMapper, TbContactRepo> {

    @Resource
    private TbContactRepo tbContactRepo;

    @Override
    protected TbContactRepo getRepo() {
        return tbContactRepo;
    }
}