package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.TbContactPo;
import com.martin.contract.persist.mapper.TbContactMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class TbContactRepo extends BaseUserRepo<TbContactMapper, TbContactPo> {
}
