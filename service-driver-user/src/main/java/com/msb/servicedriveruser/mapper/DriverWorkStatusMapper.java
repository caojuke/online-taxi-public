package com.msb.servicedriveruser.mapper;

import com.msb.internalcommon.dto.DriverWorkStatus;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DriverWorkStatusMapper {
    List<DriverWorkStatus> selectAll();
    List<DriverWorkStatus> selectById(Long id);
    List<DriverWorkStatus> selectByAny(DriverWorkStatus driverWorkStatus);
    int updateById(DriverWorkStatus driverWorkStatus);
    int insert(DriverWorkStatus driverWorkStatus);
    int removeById(DriverWorkStatus driverWorkStatus);
}
