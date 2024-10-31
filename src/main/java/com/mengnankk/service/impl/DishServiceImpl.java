package com.mengnankk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengnankk.entity.Dish;
import com.mengnankk.mapper.DishMapper;
import com.mengnankk.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>implements DishService {
}
