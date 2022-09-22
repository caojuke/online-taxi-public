package com.msb.servicedriveruser.controller;


import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private DriverUserMapper driverMapper;
    @Autowired
    private CarMapper carMapper;
    @Value("${server.port}")
    String port;
    @GetMapping("/alldriver")
    public ResponseResult getAllDriver(){
        List<DriverUser> driverUsers = driverMapper.selectAll();
        System.out.println("Port"+port+ ": 响应成功！");
        return ResponseResult.success(driverUsers);
    }
    @GetMapping("/allcar")
    public ResponseResult getAllCar(){
        List<Car> cars = carMapper.selectAll();
        System.out.println("Port"+port+ ": 响应成功！");
        return ResponseResult.success(cars);
    }

}
