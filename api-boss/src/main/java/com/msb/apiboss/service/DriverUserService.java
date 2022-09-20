package com.msb.apiboss.service;

import com.msb.apiboss.remote.ServiceDriverUserClient;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class DriverUserService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

        ResponseResult responseResult = serviceDriverUserClient.addDriverUser(driverUser);

        return responseResult;
    }
}
