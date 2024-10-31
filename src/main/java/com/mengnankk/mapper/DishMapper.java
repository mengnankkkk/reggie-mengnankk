package com.mengnankk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengnankk.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
