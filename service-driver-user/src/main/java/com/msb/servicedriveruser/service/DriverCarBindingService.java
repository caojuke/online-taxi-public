package com.msb.servicedriveruser.service;

import com.msb.internalcommon.constant.BindStatusConstant;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverCarBindingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DriverCarBindingService {

    @Autowired
    private DriverCarBindingMapper driverCarBindingMapper;
    public ResponseResult bind(DriverCarBindingRelationship bindingRelationship){
        LocalDateTime now=LocalDateTime.now();
        bindingRelationship.setBindingTime(now);
        bindingRelationship.setBindState(BindStatusConstant.DRIVER_CAR_BIND);
        int rowAffected=0;
        try {
            rowAffected = driverCarBindingMapper.insert(bindingRelationship);
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(CommonStatusEnum.INSERT_DB_FAILED.getCode(),CommonStatusEnum.INSERT_DB_FAILED.getValue(),exception.getMessage());
        }

        return ResponseResult.success("增加数据条数- "+rowAffected);
    }
    public ResponseResult unbind(DriverCarBindingRelationship bindingRelationship){

        bindingRelationship.setBindState(BindStatusConstant.DRIVER_CAR_BIND);
        List<DriverCarBindingRelationship> result=new ArrayList<>();
        try {
            result = driverCarBindingMapper.selectByAny(bindingRelationship);
            if(result.isEmpty()){
                return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getValue());
            }
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(CommonStatusEnum.SELECT_DB_FAILED.getCode(),CommonStatusEnum.SELECT_DB_FAILED.getValue(),exception.getMessage());
        }
        //查询到当前的司机和车辆在库中的信息，且处于绑定状态，下一步将它们解绑，并更新解绑时间
        DriverCarBindingRelationship relationship = result.get(0);
        LocalDateTime now=LocalDateTime.now();
        relationship.setUnbindingTime(now);
        relationship.setBindState(BindStatusConstant.DRIVER_CAR_UNBIND);
        int rowAffected = 0;
        try {
            rowAffected = driverCarBindingMapper.updateById(relationship);
        } catch (Exception exception) {
            return ResponseResult.fail(CommonStatusEnum.INSERT_DB_FAILED.getCode(),CommonStatusEnum.INSERT_DB_FAILED.getValue(),exception.getMessage());
        }

        return ResponseResult.success("已解绑- "+rowAffected);
    }
}
