package com.msb.servicedriveruser.mapper;

import com.msb.internalcommon.dto.Car;
import com.msb.internalcommon.dto.DriverCarBindingRelationship;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverCarBindingMapper {
    List<DriverCarBindingRelationship> selectAll();
    int insert(DriverCarBindingRelationship bind);
    List<DriverCarBindingRelationship> selectByAny(DriverCarBindingRelationship bind);
    int updateById(DriverCarBindingRelationship bindingRelationship);
}
