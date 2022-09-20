package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class DriverUserService {
    @Autowired
    DriverUserMapper driverUserMapper;

    public ResponseResult addDriverUser(DriverUser driverUser){
        int rowAffected=0;
        try {
            rowAffected = driverUserMapper.addDriverUser(driverUser);
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(1500,"fail to add user",exception.getMessage());
        }

        return ResponseResult.success("rowAffected= "+rowAffected);
    }
}
