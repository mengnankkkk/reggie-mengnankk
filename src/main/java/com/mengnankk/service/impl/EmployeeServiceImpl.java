package com.mengnankk.service.impl;

import com.mengnankk.entity.Employee;
import com.mengnankk.mapper.EmployeeMapper;
import com.mengnankk.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service // 确保 Spring 可以扫描到这个类
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    // 这里可以实现 EmployeeService 特定的方法
}
