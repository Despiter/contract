package com.martin.contract.controller;

import com.martin.contract.common.ResultData;
import com.martin.contract.persist.entity.TbShareOrganizationPo;
import com.martin.contract.persist.mapper.TbShareOrganizationMapper;
import com.martin.contract.persist.repo.TbShareOrganizationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/shareorganization")
public class ShareOrganizationController extends BaseController<TbShareOrganizationPo, TbShareOrganizationMapper, TbShareOrganizationRepo> {

    @Resource
    private TbShareOrganizationRepo tbShareOrganizationRepo;

    @Override
    protected TbShareOrganizationRepo getRepo() {
        return tbShareOrganizationRepo;
    }

    /**
     * 共享给他人
     */
    @RequestMapping("/share")
    public ResultData<Boolean> share(@RequestParam Long otherUserId, @RequestParam Long organizationId, @RequestParam Long userId) {
        return ResultData.of(tbShareOrganizationRepo.save(new TbShareOrganizationPo(otherUserId, userId, organizationId)));
    }
}