package com.mengnankk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mengnankk.comon.R;
import com.mengnankk.entity.Employee;
import com.mengnankk.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 员工控制器
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // 使用MD5对密码进行加密
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        // 查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper); // 查询数据库

        if (emp == null) {
            return R.error("登录失败，用户名不存在");
        }

        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败，密码错误");
        }

        if (emp.getStatus() == 0) {
            return R.error("账户被封禁");
        }

        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 员工登出
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息: {}", employee);

        // 设置默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");
        if (empId == null) {
            return R.error("请先登录");
        }
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     */
    @GetMapping("/page")
    public R<Page<Employee>> page(int page, int pageSize, String name) {
        log.info("分页查询，page={}, pageSize={}, name={}", page, pageSize, name);

        // 分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);

        // 条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {  // 修正为 hasText 以避免空白字符串的情况
            queryWrapper.like(Employee::getName, name);
        }
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        // 执行查询
        employeeService.page(pageInfo, queryWrapper);

        // 返回分页结果
        return R.success(pageInfo);
    }


    /**
     * 员工信息修改，是不是检测
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/update")
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        // 打印员工信息
        log.info("更新员工信息: {}", employee.toString());

        // 从 session 中获取当前操作员工的 ID
        Long empId = (Long) request.getSession().getAttribute("employee"); // 修正属性名 "emplpyee" 为 "employee"

        // 更新员工的更新时间和更新用户
        employee.setUpdateTime(LocalDateTime.now()); // 修正方法调用
        employee.setUpdateUser(empId);

        // 调用 service 层更新员工信息
        boolean isUpdated = employeeService.updateById(employee); // 判断更新是否成功

        // 返回更新结果
        if (isUpdated) {
            return R.success("员工信息修改成功");
        } else {
            return R.error("员工信息修改失败");
        }
    }

    /**
     * 查询信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("查询数据库");
        Employee employee=employeeService.getById(id);
        if (employee!=null){
            return R.success(employee);
        }else {
            return R.error("暂无信息");
        }
    }
}
