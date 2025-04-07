package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseRepo;
import com.martin.contract.persist.entity.ContactPo;
import com.martin.contract.persist.mapper.ContactMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class ContactRepo extends BaseRepo<ContactMapper, ContactPo> {
}
