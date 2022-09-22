package com.msb.apiboss.remote;

import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);
    @PutMapping("/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car);
    @PostMapping("/driver_car_binding_relationship/bind")
    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
    @PostMapping("/driver_car_binding_relationship/unbind")
    public ResponseResult unbindCar(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}
