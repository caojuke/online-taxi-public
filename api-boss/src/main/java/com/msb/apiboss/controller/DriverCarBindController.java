package com.msb.apiboss.controller;

import com.msb.apiboss.service.DriverCarBindService;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverCarBindController {
    @Autowired
    private DriverCarBindService driverCarBindService;
    @PostMapping("/driver_car_binding_relationship/bind")
    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship relationship){
        System.out.println("传入数据："+relationship);
        return driverCarBindService.bindCar(relationship);
    }
    @PostMapping("/driver_car_binding_relationship/unbind")
    public ResponseResult unBindCar(@RequestBody DriverCarBindingRelationship relationship){
        System.out.println("传入数据："+relationship);
        return driverCarBindService.unbindCar(relationship);
    }
}
