package com.msb.servicedriveruser.service;

import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
@Slf4j
@Transactional
public class DriverUserService {
    @Autowired
    DriverUserMapper driverUserMapper;

    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUser.setGmtCreate(now);

        int rowAffected=0;
        try {
            rowAffected = driverUserMapper.addDriverUser(driverUser);
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(1500,"fail to add user",exception.getMessage());
        }

        return ResponseResult.success("增加数据条数： "+rowAffected);
    }
    public ResponseResult updateDriverUserById(DriverUser driverUser){
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        int rowAffected=0;
        try {
            rowAffected = driverUserMapper.updateDriverUserById(driverUser);;
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(1500,"fail to update user",exception.getMessage());
        }
        return ResponseResult.success("更新数据条数： "+rowAffected);

    }
}
