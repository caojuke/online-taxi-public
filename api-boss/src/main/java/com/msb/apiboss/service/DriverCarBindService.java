package com.msb.apiboss.service;

import com.msb.apiboss.remote.ServiceDriverUserClient;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class DriverCarBindService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship relationship){

        ResponseResult responseResult = serviceDriverUserClient.bindCar(relationship);

        return responseResult;
    }
    public ResponseResult unbindCar(@RequestBody DriverCarBindingRelationship relationship){

        ResponseResult responseResult = serviceDriverUserClient.unbindCar(relationship);

        return responseResult;
    }
}
