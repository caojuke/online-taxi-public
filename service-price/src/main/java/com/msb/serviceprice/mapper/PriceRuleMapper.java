package com.msb.serviceprice.mapper;


import com.msb.internalcommon.dto.PriceRule;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface PriceRuleMapper{
    //查询方法
    List<PriceRule> selectById(int id);
    List<PriceRule> selectByAny(PriceRule priceRule);
    List<PriceRule> selectAll();

    //增加方法
    int addPriceRule(PriceRule priceRule);
}
