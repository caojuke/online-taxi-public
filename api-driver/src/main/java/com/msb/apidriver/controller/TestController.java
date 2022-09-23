package com.msb.apidriver.controller;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/internalTest")
    public ResponseResult test(){
        return ResponseResult.success("port="+port);
    }
    @GetMapping("/token")
    public ResponseResult testAccessToken(){
        return ResponseResult.success("令牌有效，可以继续访问！");
    }
}
