package com.msb.servicedriveruser.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.DriverCarConstant;
import com.msb.internalcommon.dto.DriverUser;
import com.msb.internalcommon.dto.DriverWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import com.msb.servicedriveruser.mapper.DriverWorkStatusMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@Transactional
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    @Autowired
    private DriverWorkStatusMapper driverWorkStatusMapper;
    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUser.setGmtCreate(now);
        int rowAffected2=0;
        int rowAffected=0;
        try {
            rowAffected = driverUserMapper.addDriverUser(driverUser);
            //同时，初始化一条司机工作状态信息到数据库
            DriverWorkStatus driverWorkStatus = new DriverWorkStatus();
            driverWorkStatus.setDriverId(driverUser.getId());
            driverWorkStatus.setWorkStatus(DriverCarConstant.DRIVER_WORK_STATUS_STOP);
            driverWorkStatus.setGmtCreate(now);
            driverWorkStatus.setGmtModified(now);
            rowAffected2 =driverWorkStatusMapper.insert(driverWorkStatus);
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(CommonStatusEnum.INSERT_DB_FAILED.getCode(),CommonStatusEnum.INSERT_DB_FAILED.getValue(),exception.getMessage());
        }

        return ResponseResult.success("增加数据条数- "+rowAffected);
    }
    public ResponseResult updateDriverUserById(DriverUser driverUser){
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        int rowAffected1=0;

        try {
            rowAffected1 = driverUserMapper.updateDriverUserById(driverUser);
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(CommonStatusEnum.INSERT_DB_FAILED.getCode(),CommonStatusEnum.INSERT_DB_FAILED.getValue(),exception.getMessage());
        }
        return ResponseResult.success("更新数据条数- "+rowAffected1);
    }
    public ResponseResult<DriverUser> getDriverUserByPhone(String drivePhone){
        LocalDateTime now=LocalDateTime.now();

        List<DriverUser> result=new ArrayList<>();
        try {
            result = driverUserMapper.selectByPhone(drivePhone);
            if(result.isEmpty()){
                return ResponseResult.fail(CommonStatusEnum.DRIVER__NOT_EXIST.getCode(),CommonStatusEnum.DRIVER__NOT_EXIST.getValue());
            }
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(CommonStatusEnum.SELECT_DB_FAILED.getCode(),CommonStatusEnum.SELECT_DB_FAILED.getValue(),exception.getMessage());
        }
        return ResponseResult.success(result.get(0));
    }
}
