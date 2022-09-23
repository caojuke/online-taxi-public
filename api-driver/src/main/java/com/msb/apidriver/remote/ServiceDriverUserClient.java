package com.msb.apidriver.remote;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.DriverWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.response.DriverExistResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);
    @PutMapping("/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverExistResponse> getUser(@PathVariable String driverPhone);
    @PostMapping("/driver-user-work-status")
    public ResponseResult changeStatus(@RequestBody DriverWorkStatus driverWorkStatus);
}
