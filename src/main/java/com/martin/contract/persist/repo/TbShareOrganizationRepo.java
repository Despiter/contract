package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.TbShareOrganizationPo;
import com.martin.contract.persist.mapper.TbShareOrganizationMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class TbShareOrganizationRepo extends BaseUserRepo<TbShareOrganizationMapper, TbShareOrganizationPo> {
}
