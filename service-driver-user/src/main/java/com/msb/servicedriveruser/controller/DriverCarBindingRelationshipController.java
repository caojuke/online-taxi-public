package com.msb.servicedriveruser.controller;

import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.service.DriverCarBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingService driverCarBindingService;
    @PostMapping("/driver_car_binding_relationship/bind")
    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        System.out.println(driverCarBindingRelationship.getDriverId());
        System.out.println(driverCarBindingRelationship.getCarId());
        return  driverCarBindingService.bind(driverCarBindingRelationship);
    }
    @PostMapping("/driver_car_binding_relationship/unbind")
    public ResponseResult unbindCar(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        System.out.println(driverCarBindingRelationship.getDriverId());
        System.out.println(driverCarBindingRelationship.getCarId());
        return  driverCarBindingService.unbind(driverCarBindingRelationship);
    }
}
