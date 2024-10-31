package com.mengnankk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengnankk.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
}
