package com.martin.contract.controller;

import com.martin.contract.persist.entity.OrganizationPo;
import com.martin.contract.persist.mapper.OrganizationMapper;
import com.martin.contract.persist.repo.OrganizationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/organization")
public class OrganizationController extends BaseController<OrganizationPo, OrganizationMapper, OrganizationRepo> {
    public OrganizationController(OrganizationRepo organizationRepo) {
        super(organizationRepo);
    }

}