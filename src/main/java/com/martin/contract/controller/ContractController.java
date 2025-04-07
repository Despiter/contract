package com.martin.contract.controller;

import com.martin.contract.persist.entity.ContractPo;
import com.martin.contract.persist.mapper.ContractMapper;
import com.martin.contract.persist.repo.ContractRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController<ContractPo, ContractMapper, ContractRepo> {
    public ContractController(ContractRepo contractRepo) {
        super(contractRepo);
    }
}