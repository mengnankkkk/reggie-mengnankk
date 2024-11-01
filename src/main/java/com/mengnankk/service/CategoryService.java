
package com.mengnankk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengnankk.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
