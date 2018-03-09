package com.csc.qualityplatform.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class LoginController {

    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody JSONObject params){
        JSONObject response = new JSONObject();
        response.put("token", MD5(params.getString("username")+params.getString("password")));
        System.out.println("Generate user token:"+response.getString("token"));
        return response;
    }

    @PostMapping(value = "/logout")
    public JSONObject logout(){
        JSONObject response = new JSONObject();
        return response;
    }

    @RequestMapping(value = "/info")
    public JSONObject userInfo(@RequestParam("token") String token){
        JSONObject response = new JSONObject();
        response.put("roles","admin");
        response.put("name","Admin");
        response.put("avatar","");
        return response;
    }

    private String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
