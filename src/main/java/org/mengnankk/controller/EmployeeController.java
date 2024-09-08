package org.mengnankk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.mengnankk.comon.R;
import org.mengnankk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * @param request HttpServletRequest
     * @param employee 登录的员工信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public R<com.fubukiss.rikky.entity.Employee> login(HttpServletRequest request, @RequestBody com.fubukiss.rikky.entity.Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes()); // 对密码进行 MD5 加密

        LambdaQueryWrapper<com.fubukiss.rikky.entity.Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(com.fubukiss.rikky.entity.Employee::getUsername, employee.getUsername());
        com.fubukiss.rikky.entity.Employee emp = employeeService.getOne(queryWrapper); // 查数据库

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
     * 员工退出
     * @param request HttpServletRequest
     * @return 退出结果
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param request HttpServletRequest
     * @param employee 员工信息
     * @return 新增结果
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody com.fubukiss.rikky.entity.Employee employee) {
        log.info("新增员工，员工信息: {}", employee);

        // 设置默认密码为 MD5 加密后的 "12356"
        employee.setPassword(DigestUtils.md5DigestAsHex("12356".getBytes()));

        // 设置创建时间和更新时间为当前时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 获取当前操作用户的 ID
        Long empId = (Long) request.getSession().getAttribute("employee");

        // 设置创建人和更新人为当前操作用户
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        // 保存员工信息
        employeeService.save(employee);

        return R.success("新增员工成功");
    }
}
