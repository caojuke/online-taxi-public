package com.msb.servicedriveruser.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicedriveruser.mapper.CarMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CarService {
    @Autowired
    private CarMapper carMapper;
    public ResponseResult addCar(Car car){
        LocalDateTime now=LocalDateTime.now();
        car.setGmtModified(now);
        car.setGmtCreate(now);

        int rowAffected=0;
        try {
            rowAffected = carMapper.addCar(car);
        }
        catch (Exception exception){
            log.info(exception.getMessage());
            return ResponseResult.fail(CommonStatusEnum.INSERT_DB_FAILED.getCode(),CommonStatusEnum.INSERT_DB_FAILED.getValue(),exception.getMessage());
        }

        return ResponseResult.success("增加数据条数- "+rowAffected);
    }
}
