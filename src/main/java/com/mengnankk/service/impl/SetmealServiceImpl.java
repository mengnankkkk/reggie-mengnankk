package com.mengnankk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengnankk.comon.CustomException;
import com.mengnankk.entity.Setmeal;
import com.mengnankk.entity.SetmealDish;
import com.mengnankk.mapper.SetmealMapper;
import com.mengnankk.service.SetmealDishService;
import com.mengnankk.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    public void removeWithDish(List<Long> ids) {
        // 查询是否存在正在售卖的套餐
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，无法删除");
        }

        // 删除套餐信息
        this.removeByIds(ids);

        // 删除关联的套餐-菜品信息
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
