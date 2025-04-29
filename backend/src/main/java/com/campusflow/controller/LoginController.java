package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.campusflow.pojo.Emp;
import com.campusflow.pojo.LoginInfo;
import com.campusflow.pojo.Result;
import com.campusflow.service.EmpService;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    EmpService empService;


    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("员工登录：{}",emp);
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(loginInfo);
    }
}
