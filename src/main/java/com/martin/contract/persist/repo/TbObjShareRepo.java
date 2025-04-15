package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.TbObjSharePo;
import com.martin.contract.persist.mapper.TbObjShareMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@DS("db_contract")
public class TbObjShareRepo extends BaseUserRepo<TbObjShareMapper, TbObjSharePo> {

    /**
     * 根据对象ID + 被共享人员IDs
     */
    public List<TbObjSharePo> listByObjIdAndSharedUserIds(Long objId, List<Long> userIds) {
        return list(buildBySharedUserIds(objId, userIds));
    }

    /**
     * 被共享人员IDs
     */
    public List<TbObjSharePo> listBySharedIds(Long... sharedUserIds) {
        return list(buildBySharedUserIds(null, Arrays.asList(sharedUserIds)));
    }

    /**
     * 对象+被共享人员IDs
     */
    private LambdaQueryWrapper<TbObjSharePo> buildBySharedUserIds(Long objId, List<Long> sharedUserIds) {
        return Wrappers.<TbObjSharePo>lambdaQuery().eq(TbObjSharePo::getObjId, objId).in(TbObjSharePo::getSharedUserId, sharedUserIds);
    }

}
