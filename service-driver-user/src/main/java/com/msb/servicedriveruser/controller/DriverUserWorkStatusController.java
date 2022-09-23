package com.msb.servicedriveruser.controller;

import com.msb.internalcommon.dto.DriverWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.service.DriverWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserWorkStatusController {
    @Autowired
    private DriverWorkStatusService driverWorkStatusService;
    @PostMapping("/driver-user-work-status")
    public ResponseResult changeStatus(@RequestBody DriverWorkStatus driverWorkStatus){
        Long driverId = driverWorkStatus.getDriverId();
        Integer workStatus = driverWorkStatus.getWorkStatus();
        return driverWorkStatusService.changeWorkStatus(driverId,workStatus);
    }
}
