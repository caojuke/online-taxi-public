package com.msb.servicedriveruser.controller;


import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private DriverUserMapper driverMapper;
    @Value("${server.port}")
    String port;
    @GetMapping("/test")
    public ResponseResult test(){
        List<DriverUser> driverUsers = driverMapper.selectAll();
        System.out.println("Port"+port+ ": 响应成功！");
        return ResponseResult.success(driverUsers);
    }
}
