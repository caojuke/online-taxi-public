package com.msb.servicemap.mapper;

import com.msb.internalcommon.dto.DicDistrict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {

    List<DicDistrict> selectAll();

    List<DicDistrict> selectByCode(String addressCode);

    List<DicDistrict> selectByAny(DicDistrict district);

    int removeByCode(String addressCode);

    int addDistrict(DicDistrict district);


}
