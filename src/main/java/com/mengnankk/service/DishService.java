package com.mengnankk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengnankk.dto.DishDto;
import com.mengnankk.entity.Dish;
import org.springframework.stereotype.Service;

@Service
public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
    // 你可以在此添加其他 Dish 相关的业务方法
}
