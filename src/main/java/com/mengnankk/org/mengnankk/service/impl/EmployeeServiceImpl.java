package org.mengnankk.service.impl;

import org.mengnankk.entity.Employee;
import org.mengnankk.mapper.EmployeeMapper;
import org.mengnankk.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service // 确保 Spring 可以扫描到这个类
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    // 这里可以实现 EmployeeService 特定的方法
}
