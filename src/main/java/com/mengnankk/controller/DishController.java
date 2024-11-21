package com.mengnankk.controller;

import com.mengnankk.comon.R;
import com.mengnankk.dto.DishDto;
import com.mengnankk.service.DishService;
import com.mengnankk.service.impl.DishFlavorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

@PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());

        return R.success("新增成功");
    }


}
