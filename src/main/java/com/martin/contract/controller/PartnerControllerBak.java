//package com.martin.contract.controller;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.martin.contract.common.PageRequest;
//import com.martin.contract.common.ResultData;
//import com.martin.contract.enums.ObjAuditStatus;
//import com.martin.contract.enums.ObjRole;
//import com.martin.contract.enums.ShareType;
//import com.martin.contract.persist.entity.TbObjSharePo;
//import com.martin.contract.persist.entity.TbPartnerPo;
//import com.martin.contract.persist.mapper.TbPartnerMapper;
//import com.martin.contract.persist.repo.SysUserRepo;
//import com.martin.contract.persist.repo.TbObjShareRepo;
//import com.martin.contract.persist.repo.TbPartnerRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Slf4j
//@RestController
//@RequestMapping("/partner")
//public class PartnerControllerBak extends BaseController<TbPartnerPo, TbPartnerMapper, TbPartnerRepo> {
//
//    @Resource
//    private TbPartnerRepo tbPartnerRepo;
//    @Resource
//    private SysUserRepo sysUserRepo;
//    @Resource
//    private TbObjShareRepo tbObjShareRepo;
//
//    @Override
//    protected TbPartnerRepo getRepo() {
//        return tbPartnerRepo;
//    }
//
//    /**
//     * 查询当前登录用户创建的所有渠道商客户
//     */
//    @GetMapping("/page/my")
//    public ResultData<List<TbPartnerPo>> pageMy(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
//        //仅查询审批成功的
//        pageRequest.getData().setObjAuditStatus(ObjAuditStatus.SUCCESS);
//        return ResultData.of(tbPartnerRepo.page(pageRequest));
//    }
//
//    /**
//     * 查询当前已新建待审核的渠道商客户
//     */
//    @GetMapping("/page/created")
//    public ResultData<List<TbPartnerPo>> pageCreated(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
//        TbPartnerPo query = pageRequest.getData();
//        query.setCreatedUserId(pageRequest.getUserId());
//        query.setObjAuditStatus(null);
//        return ResultData.of(tbPartnerRepo.page(pageRequest).stream()
//                //过滤出 新建 和 审批失败的
//                .filter(e -> ObjAuditStatus.unSucceed(e.getObjAuditStatus()))
//                .collect(Collectors.toList()));
//    }
//
//    /**
//     * 新建待审核的渠道商客户
//     */
//    @PostMapping("/create")
//    public ResultData<Boolean> create(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
//        TbPartnerPo query = pageRequest.getData();
//        query.setCreatedUserId(pageRequest.getUserId());
//        query.setObjAuditStatus(ObjAuditStatus.NEW);
//        return ResultData.of(tbPartnerRepo.save(query));
//    }
//
//    /**
//     * 展示可认领的渠道商
//     */
//    @GetMapping("/page/claimable")
//    public ResultData<List<TbPartnerPo>> pageClaimable(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
//        //渠道商
//        TbPartnerPo data = pageRequest.getData();
//        //先找出我已被共享的渠道商对象ID
//        Set<Long> objIds = tbObjShareRepo.listBySharedIds(pageRequest.getUserId()).stream()
//                .filter(e -> e.getObjRole() == ObjRole.PARTNER && e.getShareType() == ShareType.OBJ)
//                .map(TbObjSharePo::getObjId).collect(Collectors.toSet());
//        //再找出满足查询条件&&非我自己创建的渠道商
//        List<TbPartnerPo> tbPartnerPos = tbPartnerRepo.listNotMy(pageRequest.getUserId(), pageRequest.getData());
//        List<TbPartnerPo> collect = tbPartnerPos.stream().filter(e -> !objIds.contains(e.getId())).toList();
//        return ResultData.of(collect);
//    }
//
//    /**
//     * 执行认领行为
//     */
//    @PostMapping("/claim")
//    public ResultData<Boolean> claim(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
//        TbPartnerPo query = pageRequest.getData();
//        query.setCreatedUserId(pageRequest.getUserId());
//        query.setObjAuditStatus(ObjAuditStatus.SUCCESS);
//        return ResultData.of(tbPartnerRepo.save(query));
//    }
//
//    /**
//     * 展示可认领的渠道商
//     */
//    @GetMapping("/page/claimed")
//    public ResultData<List<TbPartnerPo>> pageClaimed(@RequestBody PageRequest<TbPartnerPo> pageRequest) {
//        //共享内容
//        List<TbObjSharePo> tbObjSharePos = tbObjShareRepo.listBySharedIds(pageRequest.getUserId())
//                .stream().filter(e -> e.getShareType() == ShareType.OBJ && e.getObjRole() == ObjRole.PARTNER).toList();
//        if (CollectionUtil.isEmpty(tbObjSharePos)) {
//            return ResultData.emptyCollection();
//        }
//        return ResultData.of(tbPartnerRepo.pageByIds(pageRequest, tbObjSharePos.stream().map(TbObjSharePo::getObjId).toList()));
//    }
//
//}