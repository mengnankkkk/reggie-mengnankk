package com.mengnankk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengnankk.dto.SetmealDto;
import com.mengnankk.entity.Setmeal;
import com.mengnankk.mapper.SetmealMapper;
import com.mengnankk.service.SetmealService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>implements SetmealService {
    /**
     * @param setmealDto
     */
    @Override
    public void saveWithDish(SetmealDto setmealDto) {

    }

    /**
     * @param ids
     */
    @Override
    public void removeWithDish(List<Long> ids) {

    }

    /**
     * @param id
     * @return
     */
    @Override
    public SetmealDto getDate(Long id) {
        return null;
    }
}
