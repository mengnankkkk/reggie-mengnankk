package com.mengnankk.controller;

import com.mengnankk.service.DishService;
import com.mengnankk.service.impl.DishFlavorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorServiceImpl dishFlavorService;




}
