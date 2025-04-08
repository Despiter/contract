package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.TbPartnerPo;
import com.martin.contract.persist.mapper.TbPartnerMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class TbPartnerRepo extends BaseUserRepo<TbPartnerMapper, TbPartnerPo> {
}
