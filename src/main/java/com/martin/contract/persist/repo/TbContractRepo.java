package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.TbContractPo;
import com.martin.contract.persist.mapper.TbContractMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class TbContractRepo extends BaseUserRepo<TbContractMapper, TbContractPo> {
}
