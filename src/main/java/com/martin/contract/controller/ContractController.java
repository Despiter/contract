package com.martin.contract.controller;

import com.martin.contract.persist.entity.TbContractPo;
import com.martin.contract.persist.mapper.TbContractMapper;
import com.martin.contract.persist.repo.TbContractRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController<TbContractPo, TbContractMapper, TbContractRepo> {

    @Resource
    private TbContractRepo tbContractRepo;

    @Override
    protected TbContractRepo getRepo() {
        return tbContractRepo;
    }
}