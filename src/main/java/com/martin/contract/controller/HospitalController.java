package com.martin.contract.controller;

import com.martin.contract.common.PageRequest;
import com.martin.contract.common.ResultData;
import com.martin.contract.enums.ObjAuditStatus;
import com.martin.contract.enums.ObjRole;
import com.martin.contract.enums.ShareStatus;
import com.martin.contract.enums.ShareType;
import com.martin.contract.persist.entity.TbHospitalPo;
import com.martin.contract.persist.entity.TbObjSharePo;
import com.martin.contract.persist.mapper.TbHospitalMapper;
import com.martin.contract.persist.repo.TbHospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/hospital")
public class HospitalController extends BaseUserController<TbHospitalPo, TbHospitalMapper, TbHospitalRepo> {

    @Resource
    private TbHospitalRepo tbHospitalRepo;

    @Override
    protected TbHospitalRepo getRepo() {
        return tbHospitalRepo;
    }

    @Override
    protected boolean sharableFilter(TbObjSharePo sharePo) {
        return sharePo.getShareType() == ShareType.OBJ && sharePo.getObjRole() == ObjRole.HOSPITAL;
    }

    @Override
    protected TbObjSharePo initSharePo() {
        return TbObjSharePo.builder().objRole(ObjRole.HOSPITAL).shareType(ShareType.OBJ).shareStatus(ShareStatus.SUCCESS).build();
    }

    /**
     * 查询当前已新建待审核的医院客户
     */
    @GetMapping("/page/created")
    public ResultData<List<TbHospitalPo>> pageCreated(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
        TbHospitalPo query = pageRequest.getData();
        query.setCreatedUserId(pageRequest.getUserId());
        query.setObjAuditStatus(null);
        return ResultData.of(tbHospitalRepo.page(pageRequest).stream()
                //过滤出 新建 和 审批失败的
                .filter(e -> ObjAuditStatus.unSucceed(e.getObjAuditStatus()))
                .collect(Collectors.toList()));
    }

    /**
     * 新建待审核的医院客户
     */
    @PostMapping("/create")
    public ResultData<Boolean> create(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
        TbHospitalPo query = pageRequest.getData();
        query.setCreatedUserId(pageRequest.getUserId());
        query.setObjAuditStatus(ObjAuditStatus.NEW);
        return ResultData.of(tbHospitalRepo.save(query));
    }
}