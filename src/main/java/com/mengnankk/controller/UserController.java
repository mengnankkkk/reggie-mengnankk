package com.mengnankk.controller;

import com.mengnankk.comon.R;
import com.mengnankk.entity.User;
import com.mengnankk.service.UserService;
import com.mengnankk.utils.ValidateCodeUtils;
import com.sun.jmx.snmp.agent.SnmpMibSubRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.invoke.LambdaMetafactory;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService  userService;
    public R<String> sendMvg(@RequestBody User user, HttpSession session){
        String phone = user.getEmail();
        String code = ValidateCodeUtils.generateValidateCode(4).toString();
        log.info("code={}",code);
        return R.success("发送成功");

    }
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        log.info(map.toString());
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        Object codeInsesion = session.getAttribute(phone);
        if (codeInsesion!=null&&codeInsesion.equals(code)){
            LambdaMetafactory lambdaMetafactory = new LambdaMetafactory();
            lambdaMetafactory.equals(User::getEmail);
            User user = userService.getOne(lambdaMetafactory);
            if (user==null){
                user = new User();
                user.setEmail(phone);
                user.setStatus(1);
                userService.save(user);
            }
            return R.success(user);
        }
        return R.error("失败");

    }
}
