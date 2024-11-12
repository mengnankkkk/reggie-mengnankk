package com.mengnankk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengnankk.entity.DishFlavor;
import com.mengnankk.mapper.DishFlavorMapper;
import com.mengnankk.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
