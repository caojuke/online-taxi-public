package com.msb.servicedriveruser.mapper;

import com.msb.internalcommon.dto.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    List<Car> selectAll();
    int addCar(Car car);
}
