package com.msb.servicedriveruser.controller;


import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import com.msb.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private DriverUserService driverUserService;
    @Value("${server.port}")
    String port;
    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        System.out.println(driverUser);
        return driverUserService.addDriverUser(driverUser);
    }
    @PutMapping("/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        System.out.println(driverUser);
        return driverUserService.updateDriverUserById(driverUser);
    }
}
