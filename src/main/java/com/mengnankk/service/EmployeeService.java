package com.mengnankk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengnankk.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends IService<Employee> {
    // 你可以在这里声明 EmployeeService 特定的方法
}
