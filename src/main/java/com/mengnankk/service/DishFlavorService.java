package com.mengnankk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengnankk.comon.R;
import com.mengnankk.dto.DishDto;
import com.mengnankk.entity.DishFlavor;

public interface DishFlavorService extends IService<DishFlavor> {
    // 保存菜品及其口味
    R<String> saveWithFlavor(DishDto dishDto);
}
