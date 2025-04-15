package com.martin.contract.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.martin.contract.common.PageRequest;
import com.martin.contract.common.Request;
import com.martin.contract.common.ResultData;
import com.martin.contract.dto.ShareToUsersDto;
import com.martin.contract.enums.ObjRole;
import com.martin.contract.enums.ShareType;
import com.martin.contract.persist.base.BasePo;
import com.martin.contract.persist.base.BaseUserPo;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.SysUserPo;
import com.martin.contract.persist.entity.TbObjSharePo;
import com.martin.contract.persist.repo.SysUserRepo;
import com.martin.contract.persist.repo.TbObjShareRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseUserController<T extends BaseUserPo, M extends BaseMapper<T>, R extends BaseUserRepo<M, T>> {

    protected abstract R getRepo();
    @Resource
    private SysUserRepo sysUserRepo;
    @Resource
    private TbObjShareRepo tbObjShareRepo;

    protected abstract boolean sharableFilter(TbObjSharePo sharePo);

    protected abstract TbObjSharePo initSharePo();
    
    /**
     * 查询当前登录用户创建的
     */
    @GetMapping("/page/my")
    public ResultData<List<T>> pageMy(@RequestBody PageRequest<T> pageRequest) {
        return ResultData.of(getRepo().page(pageRequest));
    }

    /**
     * 展示出可共享的人员
     */
    @GetMapping("/page/sharableuser")
    public ResultData<List<SysUserPo>> pageSharableUser(@RequestBody PageRequest<T> pageRequest) {
        //先找出所有其他人员
        List<SysUserPo> othUsers = sysUserRepo.listNotMe(pageRequest.getUserId());
        List<Long> allUserIds = othUsers.stream().map(BasePo::getId).toList();
        //查询共享表, 查看上述用户中哪些已经获取到该对象的共享权
        List<TbObjSharePo> tbObjSharedPos = tbObjShareRepo.listByObjIdAndSharedUserIds(pageRequest.getData().getId(), allUserIds).stream()
                //过滤器
                .filter(this::sharableFilter).toList();
        List<Long> sharedUserIds = tbObjSharedPos.stream().map(BasePo::getId).toList();
        //对比数据, 获取可以共享的人员
        List<SysUserPo> shareableUsers = othUsers.stream().filter(user -> !sharedUserIds.contains(user.getId())).toList();
        return ResultData.of(shareableUsers);
    }

    /**
     * 执行共享操作
     */
    @PostMapping("/share")
    public ResultData<Boolean> share(@RequestBody Request<ShareToUsersDto> request) {
        //请求内容
        ShareToUsersDto shareToUsersDto = request.getData();
        //构造共享信息
        List<TbObjSharePo> result = shareToUsersDto.getSharedUserIds().stream().map(sharedUserId -> {
            TbObjSharePo tbObjSharePo = initSharePo();
            tbObjSharePo.setCreatedUserId(request.getUserId());
            tbObjSharePo.setSharedUserId(sharedUserId);
            tbObjSharePo.setObjId(shareToUsersDto.getObjId());
            return tbObjSharePo;
        }).collect(Collectors.toList());
        return ResultData.of(tbObjShareRepo.saveBatch(result));
    }

    /**
     * 查询当前登录用户下属创建的所有医院客户
     */
    @GetMapping("/page/under")
    public ResultData<List<T>> pageUnder(@RequestBody PageRequest<T> pageRequest) {
        //全部下属用户
        List<SysUserPo> unders = sysUserRepo.listUnders(pageRequest.getUserId());
        List<Long> underUserIds = unders.stream().map(BasePo::getId).toList();

        if (CollectionUtil.isEmpty(underUserIds)) {
            return ResultData.emptyCollection();
        }
        return ResultData.of(getRepo().listByUserIds(underUserIds, pageRequest.getData()));
    }

    /**
     * 查询当前登录用户被共享的所有医院客户
     */
    @GetMapping("/page/shared")
    public ResultData<List<T>> pageShared(@RequestBody PageRequest<T> pageRequest) {
        //共享内容
        List<TbObjSharePo> tbObjSharePos = tbObjShareRepo.listBySharedIds(pageRequest.getUserId())
                .stream().filter(e -> e.getShareType() == ShareType.OBJ && e.getObjRole() == ObjRole.HOSPITAL).toList();
        if (CollectionUtil.isEmpty(tbObjSharePos)) {
            return ResultData.emptyCollection();
        }
        return ResultData.of(getRepo().pageByIds(pageRequest, tbObjSharePos.stream().map(TbObjSharePo::getObjId).toList()));
    }

}
