package com.msb.apipassenger.controller;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/auth")
    public ResponseResult auth(){
        return ResponseResult.success("token ok");
    }
    @GetMapping("/internalTest")
    public ResponseResult noauth(){
        return ResponseResult.success("token ok");
    }
}
