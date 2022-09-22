package com.msb.servicedriveruser.controller;


import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DriverExistResponse;
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
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverExistResponse> getUser(@PathVariable String driverPhone) {
        System.out.println(driverPhone);
        ResponseResult<DriverUser> result = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverDb = result.getData();
        if(driverDb==null){
            return ResponseResult.fail(new DriverExistResponse("号码不存在",0));
        }else {
            return ResponseResult.success(new DriverExistResponse(driverDb.getDriverPhone(),1));
        }
    }
}
