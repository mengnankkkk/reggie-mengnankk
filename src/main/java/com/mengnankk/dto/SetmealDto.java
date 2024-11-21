package com.mengnankk.dto;


import com.mengnankk.entity.Setmeal;
import com.mengnankk.entity.SetmealDish;
import lombok.Data;

import java.util.List;


@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
