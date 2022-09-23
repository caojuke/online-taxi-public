package com.msb.apidriver.service;


import com.msb.apidriver.remote.ServiceDriverUserClient;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.DriverWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DriverUserService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addDriverUser(DriverUser driverUser){

        ResponseResult responseResult = serviceDriverUserClient.addDriverUser(driverUser);

        return responseResult;
    }
    public ResponseResult updateDriverUser(DriverUser driverUser){

        ResponseResult responseResult = serviceDriverUserClient.updateDriverUser(driverUser);

        return responseResult;
    }
    public ResponseResult changeDriverWorkStatus(DriverWorkStatus driverWorkStatus){

        return serviceDriverUserClient.changeStatus(driverWorkStatus);
    }
}
