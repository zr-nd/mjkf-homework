package com.example.mjkf.controller;


import com.example.mjkf.serviceImpl.Login;
import com.example.mjkf.vo.UserVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@MapperScan("com.example.mjkf.dao")
@ComponentScan(basePackages = {"com.example.mjkf.serviceImpl", "com.example.mjkf.dao"})

public class LoginController {

    @Autowired
    private Login loginInter;

    LoginController(Login loginInter){this.loginInter = loginInter;}

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public boolean getUserPwd(@RequestBody(required=false) UserVO user){
        return(loginInter.hasUser(user.getUsername(),user.getPassword()));
    }
}
