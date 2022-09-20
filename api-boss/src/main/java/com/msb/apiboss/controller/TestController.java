package com.msb.apiboss.controller;


import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    String port;
    @GetMapping("/test")
    public ResponseResult test(){
        System.out.println("Port"+port+ ": 响应成功！");
        return ResponseResult.success("啥也不是");
    }
}
