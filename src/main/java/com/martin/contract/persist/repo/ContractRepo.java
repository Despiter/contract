package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseRepo;
import com.martin.contract.persist.entity.ContractPo;
import com.martin.contract.persist.mapper.ContractMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class ContractRepo extends BaseRepo<ContractMapper, ContractPo> {
}
