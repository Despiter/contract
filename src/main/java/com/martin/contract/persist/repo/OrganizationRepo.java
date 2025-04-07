package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseRepo;
import com.martin.contract.persist.entity.OrganizationPo;
import com.martin.contract.persist.mapper.OrganizationMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class OrganizationRepo extends BaseRepo<OrganizationMapper, OrganizationPo> {
}
