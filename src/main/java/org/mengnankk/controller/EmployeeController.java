package org.mengnankk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.mengnankk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public com.fubukiss.rikky.common.R<com.fubukiss.rikky.entity.Employee>login(HttpServletRequest request,@RequestBody com.fubukiss.rikky.entity.Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());//对密码进行md5加密

        LambdaQueryWrapper<com.fubukiss.rikky.entity.Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(com.fubukiss.rikky.entity.Employee::getUsername,employee.getUsername());
         com.fubukiss.rikky.entity.Employee emp = employeeService.getOne(queryWrapper);//查数据库

        if (emp==null){
            return com.fubukiss.rikky.common.R.error("登录失败1");
        }

        if (!emp.getPassword().equals(password)){
            return com.fubukiss.rikky.common.R.error("登录失败2");
        }//匹配密码

        if (emp.getStatus() ==0){
            return com.fubukiss.rikky.common.R.error("账户被封禁");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return com.fubukiss.rikky.common.R.success(emp);


    }
    @PostMapping("/logout")
    public com.fubukiss.rikky.common.R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return com.fubukiss.rikky.common.R.success("退出成功");
    }


}
