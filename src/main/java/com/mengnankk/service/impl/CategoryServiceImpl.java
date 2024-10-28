package com.mengnankk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengnankk.entity.Category;
import com.mengnankk.mapper.CategoryMapper;
import com.mengnankk.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
