package com.martin.contract.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.martin.contract.persist.entity.TbContactPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbContactMapper extends BaseMapper<TbContactPo> {
}
