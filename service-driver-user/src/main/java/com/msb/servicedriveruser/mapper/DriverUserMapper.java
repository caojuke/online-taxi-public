package com.msb.servicedriveruser.mapper;

import com.msb.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverUserMapper {
    List<DriverUser> selectAll();
    int addDriverUser(DriverUser driverUser);
    int updateDriverUserById(DriverUser driverUser);
}
