package com.martin.contract.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.martin.contract.common.PageRequest;
import com.martin.contract.common.ResultData;
import com.martin.contract.enums.ObjRole;
import com.martin.contract.enums.ShareType;
import com.martin.contract.persist.base.BasePo;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.entity.TbContactPo;
import com.martin.contract.persist.entity.TbObjSharePo;
import com.martin.contract.persist.mapper.TbContactMapper;
import com.martin.contract.persist.repo.SysUserRepo;
import com.martin.contract.persist.repo.TbContactRepo;
import com.martin.contract.persist.repo.TbObjShareRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController extends BaseController<TbContactPo, TbContactMapper, TbContactRepo> {

    @Resource
    private TbContactRepo tbContactRepo;
    @Resource
    private SysUserRepo sysUserRepo;
    @Resource
    private TbObjShareRepo tbObjShareRepo;

    @Override
    protected TbContactRepo getRepo() {
        return tbContactRepo;
    }

    @GetMapping("/page/myhospital")
    public ResultData<List<TbContactPo>> pageMyHospital(PageRequest<TbContactPo> pageRequest) {
        TbContactPo query = pageRequest.getData();
        query.setCreatedUserId(pageRequest.getUserId());
        query.setObjRole(ObjRole.HOSPITAL);
        return ResultData.of(tbContactRepo.page(pageRequest));
    }

    /**
     * 查询当前登录用户下属创建的所有医院客户联系人
     */
    @GetMapping("/page/under")
    public ResultData<List<TbContactPo>> pageUnder(@RequestBody PageRequest<TbContactPo> pageRequest) {
        //全部下属用户
        List<SysUserPo> unders = sysUserRepo.listUnders(pageRequest.getUserId());
        List<Long> underUserIds = unders.stream().map(BasePo::getId).toList();

        if (CollectionUtil.isEmpty(underUserIds)) {
            return ResultData.emptyCollection();
        }
        return ResultData.of(tbContactRepo.listByUserIds(underUserIds, pageRequest.getData())
                .stream().filter(e -> e.getObjRole() == ObjRole.HOSPITAL).toList());
    }

    /**
     * 查询当前登录用户被共享的所有医院客户联系人
     */
    @GetMapping("/page/shared")
    public ResultData<List<TbContactPo>> pageShared(@RequestBody PageRequest<TbContactPo> pageRequest) {
        pageRequest.getData().setId(null);
        //共享内容
        List<TbObjSharePo> tbObjSharePos = tbObjShareRepo.listBySharedIds(pageRequest.getUserId())
                .stream().filter(e -> e.getShareType() == ShareType.CONTACT && e.getObjRole() == ObjRole.HOSPITAL).toList();

        if (CollectionUtil.isEmpty(tbObjSharePos)) {
            return ResultData.emptyCollection();
        }
        return ResultData.of(tbContactRepo.pageByIds(pageRequest, tbObjSharePos.stream().map(TbObjSharePo::getObjId).toList()));
    }

    /**
     * 新建待审核的医院客户联系人
     */
    @PostMapping("/create")
    public ResultData<Boolean> create(@RequestBody PageRequest<TbContactPo> pageRequest) {
        TbContactPo query = pageRequest.getData();
        query.setCreatedUserId(pageRequest.getUserId());
        return ResultData.of(tbContactRepo.save(query));
    }

    @GetMapping("/page/mypartner")
    public ResultData<List<TbContactPo>> pageMyPartner(PageRequest<TbContactPo> pageRequest) {
        TbContactPo query = pageRequest.getData();
        query.setCreatedUserId(pageRequest.getUserId());
        query.setObjRole(ObjRole.PARTNER);
        return ResultData.of(tbContactRepo.page(pageRequest));
    }

}