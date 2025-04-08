package com.martin.contract.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.martin.contract.common.PageRequest;
import com.martin.contract.common.ResultData;
import com.martin.contract.persist.base.BasePo;
import com.martin.contract.persist.entity.TbPartnerPo;
import com.martin.contract.persist.entity.TbShareOrganizationPo;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.mapper.TbPartnerMapper;
import com.martin.contract.persist.repo.TbPartnerRepo;
import com.martin.contract.persist.repo.TbShareOrganizationRepo;
import com.martin.contract.persist.repo.SysUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/organization")
public class OrganizationController extends BaseController<TbPartnerPo, TbPartnerMapper, TbPartnerRepo> {

    @Resource
    private TbPartnerRepo tbPartnerRepo;
    @Resource
    private SysUserRepo sysUserRepo;
    @Resource
    private TbShareOrganizationRepo tbShareOrganizationRepo;

    @Override
    protected TbPartnerRepo getRepo() {
        return tbPartnerRepo;
    }

    /**
     * 我的机构
     */
    @GetMapping("/page/mine")
    public ResultData<List<TbPartnerPo>> pageMy(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
        TbPartnerPo query = Optional.ofNullable(pageRequest.getData()).orElse(TbPartnerPo.builder().build());
        query.setUserId(pageRequest.getData().getUserId());
        pageRequest.setData(query);
        return ResultData.of(tbPartnerRepo.page(pageRequest));
    }

    /**
     * 下属机构
     */
    @GetMapping("/page/under")
    public ResultData<List<TbPartnerPo>> pageUnderOrganization(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
        List<SysUserPo> unders = sysUserRepo.listByParentId(pageRequest.getData().getUserId());
        List<Long> ids = unders.stream().map(BasePo::getId).toList();
        if (CollectionUtil.isEmpty(ids)) {
            return ResultData.emptyCollection();
        }
        return ResultData.of(tbPartnerRepo.page(ids, pageRequest.getData()));
    }

    /**
     * 共享的机构
     */
    @GetMapping("/page/share")
    public ResultData<List<TbPartnerPo>> pageShareOrganization(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
        List<TbShareOrganizationPo> tbShareOrganizationPos = tbShareOrganizationRepo.listMyShares(pageRequest.getUserId());
        if (CollectionUtil.isEmpty(tbShareOrganizationPos)) {
            return ResultData.emptyCollection();
        }
        return ResultData.of(tbPartnerRepo.list(tbShareOrganizationPos.stream().map(BasePo::getId).toList(), pageRequest.getData()));
    }
}