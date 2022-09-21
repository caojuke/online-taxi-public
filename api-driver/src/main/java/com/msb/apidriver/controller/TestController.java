package com.msb.apidriver.controller;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/test")
    public ResponseResult test(){
        return ResponseResult.success("port="+port);
    }
}
