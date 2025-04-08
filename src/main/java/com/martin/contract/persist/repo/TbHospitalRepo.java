package com.martin.contract.persist.repo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.martin.contract.persist.base.BaseUserRepo;
import com.martin.contract.persist.entity.TbHospitalPo;
import com.martin.contract.persist.mapper.TbHospitalMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("db_contract")
public class TbHospitalRepo extends BaseUserRepo<TbHospitalMapper, TbHospitalPo> {
}
