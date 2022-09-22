package com.msb.apidriver.remote;

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
}