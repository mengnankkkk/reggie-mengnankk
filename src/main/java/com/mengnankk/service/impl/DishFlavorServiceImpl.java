package com.mengnankk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengnankk.comon.R;
import com.mengnankk.dto.DishDto;
import com.mengnankk.entity.Dish;
import com.mengnankk.entity.DishFlavor;
import com.mengnankk.mapper.DishFlavorMapper;
import com.mengnankk.service.DishFlavorService;
import com.mengnankk.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

    @Autowired
    private DishService dishService;  // 注入 DishService 用于保存菜品

    @Override
    public R<String> saveWithFlavor(DishDto dishDto) {
        log.info("开始录入信息");
        // 1. 保存菜品信息
        Dish dish = new Dish();
        // 假设 DishDto 中有名称、类别等属性，复制到 Dish 实体
        dish.setName(dishDto.getName());
        dish.setCategoryId(dishDto.getCategoryId());
        // 其他属性的复制...

        // 保存菜品实体
        dishService.save(dish);  // 调用 DishService 保存菜品

        // 2. 获取保存后的菜品 ID
        Long dishID = dish.getId();
        if (dishID == null) {
            throw new RuntimeException("保存菜品时未能生成 ID");
        }

        // 3. 保存口味数据
        List<DishFlavor> flavors = dishDto.getFlavors().stream().map(item -> {
            item.setDishId(dishID);  // 设置每个口味的菜品 ID
            return item;
        }).collect(Collectors.toList());

        // 批量保存口味
        this.saveBatch(flavors);
        return R.success("新增成功");
    }
}
