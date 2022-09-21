package com.msb.apidriver.controller;


import com.msb.apidriver.service.DriverUserService;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private DriverUserService driverUserService;
    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        System.out.println(driverUser);
        return driverUserService.addDriverUser(driverUser);
    }
    @PutMapping("/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        System.out.println(driverUser);
        return driverUserService.updateDriverUser(driverUser);
    }
}
