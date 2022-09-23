package com.msb.servicedriveruser.service;

import com.google.j2objc.annotations.AutoreleasePool;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.DriverWorkStatus;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverWorkStatusService {
    @Autowired
    private DriverWorkStatusMapper driverWorkStatusMapper;
    public ResponseResult getAll(){
        List<DriverWorkStatus> driverWorkStatuses=new ArrayList<>();
        try {
            driverWorkStatuses = driverWorkStatusMapper.selectAll();
        } catch (Exception exception) {
            return ResponseResult.fail(CommonStatusEnum.SELECT_DB_FAILED.getCode(),CommonStatusEnum.SELECT_DB_FAILED.getValue());
        }
        return ResponseResult.success(driverWorkStatuses);
    }
    public ResponseResult insert(DriverWorkStatus driverWorkStatus){
        int rowAffected=0;
        try {
            rowAffected = driverWorkStatusMapper.insert(driverWorkStatus);
        } catch (Exception exception) {
            return ResponseResult.fail(CommonStatusEnum.INSERT_DB_FAILED.getCode(),CommonStatusEnum.INSERT_DB_FAILED.getValue());
        }
        return ResponseResult.success("插入数据："+rowAffected);
    }
    public ResponseResult changeWorkStatus(Long driverId,Integer workStatus){
        List<DriverWorkStatus> driverWorkStatuses=new ArrayList<>();
        //查询
        try {
            driverWorkStatuses = driverWorkStatusMapper.selectById(driverId);
        } catch (Exception exception) {
            return ResponseResult.fail(CommonStatusEnum.SELECT_DB_FAILED.getCode(),CommonStatusEnum.SELECT_DB_FAILED.getValue());
        }
        //是否存在
        if (driverWorkStatuses.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.SELECT_NO_FOUND.getCode(),CommonStatusEnum.SELECT_NO_FOUND.getValue());
        }
        //设置更新的内容
        LocalDateTime now=LocalDateTime.now();
        DriverWorkStatus targetDriver = driverWorkStatuses.get(0);
        targetDriver.setWorkStatus(workStatus);
        targetDriver.setGmtModified(now);
        //更新
        try {
            driverWorkStatusMapper.updateById(targetDriver);
        } catch (Exception exception) {
            return ResponseResult.fail(CommonStatusEnum.UPDATE_DB_FAILED.getCode(),CommonStatusEnum.UPDATE_DB_FAILED.getValue());
        }

        return ResponseResult.success("更新成功");
    }
}
