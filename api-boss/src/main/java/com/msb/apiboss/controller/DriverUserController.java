package com.msb.apiboss.controller;

import com.msb.apiboss.service.DriverUserService;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {
    @Autowired
    private DriverUserService driverUserService;
    @PostMapping("driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        System.out.println(driverUser);
        return driverUserService.addDriverUser(driverUser);
    }
}
