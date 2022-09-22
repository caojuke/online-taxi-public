package com.msb.apiboss.controller;

import com.msb.apiboss.remote.ServiceDriverUserClient;
import com.msb.apiboss.service.CarService;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        System.out.println("传入数据："+car);
        return carService.addCar(car);
    }
}
