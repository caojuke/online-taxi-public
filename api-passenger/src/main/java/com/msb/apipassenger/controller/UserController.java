package com.msb.apipassenger.controller;

import com.msb.apipassenger.service.UserService;
import com.msb.internalcommon.dto.ResponseResult;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        //从http请求头中获取令牌
        String accessToken=request.getHeader("Authorization");

        return userService.getUserByAccessToken(accessToken);
    }
}
