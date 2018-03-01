package com.csc.qualityplatform.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login")
    public JSONObject loginController(){
        System.out.println("User login!");
        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("msg", "SUCCESS");
        JSONObject user = new JSONObject();
        user.put("id", 1);
        user.put("username", "admin");
        user.put("password", "123456");
        user.put("name", "Admin");
        user.put("id", 1);
        response.put("user", user);
        return response;
    }
}
