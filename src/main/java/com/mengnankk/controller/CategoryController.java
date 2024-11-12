package com.mengnankk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengnankk.comon.R;
import com.mengnankk.entity.Category;
import com.mengnankk.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("添加分类成功");
    }
    @PostMapping("/page")
    public R<Page<Category>> page(int page, int pageSize) {
        Page<Category> categoryPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);

        categoryService.page(categoryPage, queryWrapper);

        return R.success(categoryPage);
    }
    @DeleteMapping//注意是ids哦
    public R<String> delete(Long ids){
        log.info("删除id为{}",ids);
        categoryService.remove(ids);
        return R.success("分类信息删除成功");

    }

    /**
     * 根据条件查询数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        categoryService.list(lambdaQueryWrapper);
        List<Category> list = categoryService.list(lambdaQueryWrapper);

        return R.success(list);

    }
}
