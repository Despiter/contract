//package com.martin.contract.controller;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.martin.contract.common.PageRequest;
//import com.martin.contract.common.Request;
//import com.martin.contract.common.ResultData;
//import com.martin.contract.dto.ShareToUsersDto;
//import com.martin.contract.enums.ObjAuditStatus;
//import com.martin.contract.enums.ObjRole;
//import com.martin.contract.enums.ShareStatus;
//import com.martin.contract.enums.ShareType;
//import com.martin.contract.persist.base.BasePo;
//import com.martin.contract.persist.entity.SysUserPo;
//import com.martin.contract.persist.entity.TbHospitalPo;
//import com.martin.contract.persist.entity.TbObjSharePo;
//import com.martin.contract.persist.mapper.TbHospitalMapper;
//import com.martin.contract.persist.repo.SysUserRepo;
//import com.martin.contract.persist.repo.TbHospitalRepo;
//import com.martin.contract.persist.repo.TbObjShareRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@RestController
//@RequestMapping("/hospital")
//public class HospitalControllerBak extends BaseController<TbHospitalPo, TbHospitalMapper, TbHospitalRepo> {
//
//    @Resource
//    private TbHospitalRepo tbHospitalRepo;
//    @Resource
//    private SysUserRepo sysUserRepo;
//    @Resource
//    private TbObjShareRepo tbObjShareRepo;
//
//    @Override
//    protected TbHospitalRepo getRepo() {
//        return tbHospitalRepo;
//    }
//
//    /**
//     * 查询当前登录用户创建的所有医院客户
//     */
//    @GetMapping("/page/my")
//    public ResultData<List<TbHospitalPo>> pageMy(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
//        //仅查询审批成功的
//        pageRequest.getData().setObjAuditStatus(ObjAuditStatus.SUCCESS);
//        return ResultData.of(tbHospitalRepo.page(pageRequest));
//    }
//
//    /**
//     * 展示出可共享的人员
//     */
//    @GetMapping("/page/sharableuser")
//    public ResultData<List<SysUserPo>> pageSharableUser(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
//        //先找出所有其他人员
//        List<SysUserPo> othUsers = sysUserRepo.listNotMe(pageRequest.getUserId());
//        List<Long> allUserIds = othUsers.stream().map(BasePo::getId).toList();
//        //查询共享表, 查看上述用户中哪些已经获取到该对象的共享权
//        List<TbObjSharePo> tbObjSharedPos = tbObjShareRepo.listByObjIdAndSharedUserIds(pageRequest.getData().getId(), allUserIds)
//                .stream().filter(e -> e.getShareType() == ShareType.OBJ && e.getObjRole() == ObjRole.HOSPITAL).toList();
//        List<Long> sharedUserIds = tbObjSharedPos.stream().map(BasePo::getId).toList();
//        //对比数据, 获取可以共享的人员
//        List<SysUserPo> shareableUsers = othUsers.stream().filter(user -> !sharedUserIds.contains(user.getId())).toList();
//        return ResultData.of(shareableUsers);
//    }
//
//    /**
//     * 执行共享操作
//     */
//    @PostMapping("/share")
//    public ResultData<Boolean> share(@RequestBody Request<ShareToUsersDto> request) {
//        //请求内容
//        ShareToUsersDto shareToUsersDto = request.getData();
//        //构造共享信息
//        List<TbObjSharePo> result = shareToUsersDto.getSharedUserIds().stream().map(sharedUserId -> TbObjSharePo.builder()
//                .createdUserId(request.getUserId())
//                .sharedUserId(sharedUserId)
//                .objId(shareToUsersDto.getObjId())
//                .objRole(ObjRole.HOSPITAL)
//                .shareType(ShareType.OBJ)
//                .shareStatus(ShareStatus.SUCCESS)
//                .build()
//        ).collect(Collectors.toList());
//        return ResultData.of(tbObjShareRepo.saveBatch(result));
//    }
//
//    /**
//     * 查询当前登录用户下属创建的所有医院客户
//     */
//    @GetMapping("/page/under")
//    public ResultData<List<TbHospitalPo>> pageUnder(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
//        //全部下属用户
//        List<SysUserPo> unders = sysUserRepo.listUnders(pageRequest.getUserId());
//        List<Long> underUserIds = unders.stream().map(BasePo::getId).toList();
//
//        if (CollectionUtil.isEmpty(underUserIds)) {
//            return ResultData.emptyCollection();
//        }
//        return ResultData.of(tbHospitalRepo.listByUserIds(underUserIds, pageRequest.getData()));
//    }
//
//    /**
//     * 查询当前登录用户被共享的所有医院客户
//     */
//    @GetMapping("/page/shared")
//    public ResultData<List<TbHospitalPo>> pageShared(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
//        //共享内容
//        List<TbObjSharePo> tbObjSharePos = tbObjShareRepo.listBySharedIds(pageRequest.getUserId())
//                .stream().filter(e -> e.getShareType() == ShareType.OBJ && e.getObjRole() == ObjRole.HOSPITAL).toList();
//
//        if (CollectionUtil.isEmpty(tbObjSharePos)) {
//            return ResultData.emptyCollection();
//        }
//        return ResultData.of(tbHospitalRepo.pageByIds(pageRequest, tbObjSharePos.stream().map(TbObjSharePo::getObjId).toList()));
//    }
//
//    /**
//     * 查询当前已新建待审核的医院客户
//     */
//    @GetMapping("/page/created")
//    public ResultData<List<TbHospitalPo>> pageCreated(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
//        TbHospitalPo query = pageRequest.getData();
//        query.setCreatedUserId(pageRequest.getUserId());
//        query.setObjAuditStatus(null);
//        return ResultData.of(tbHospitalRepo.page(pageRequest).stream()
//                //过滤出 新建 和 审批失败的
//                .filter(e -> ObjAuditStatus.unSucceed(e.getObjAuditStatus()))
//                .collect(Collectors.toList()));
//    }
//
//    /**
//     * 新建待审核的医院客户
//     */
//    @PostMapping("/create")
//    public ResultData<Boolean> create(@RequestBody PageRequest<TbHospitalPo> pageRequest) {
//        TbHospitalPo query = pageRequest.getData();
//        query.setCreatedUserId(pageRequest.getUserId());
//        query.setObjAuditStatus(ObjAuditStatus.NEW);
//        return ResultData.of(tbHospitalRepo.save(query));
//    }
//}