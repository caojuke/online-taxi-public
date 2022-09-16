package com.msb.serviceprice.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.PriceRule;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.msb.serviceprice.mapper.PriceRuleMapper;
import com.msb.serviceprice.remote.ServiceMapClient;
import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    ServiceMapClient serviceMapClient;
    @Autowired
    PriceRuleMapper priceRuleMapper;
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO){
        log.info("调用map服务，得到距离和时间");
        ResponseResult<DirectionResponse> result = serviceMapClient.driving(forecastPriceDTO);
        DirectionResponse data = result.getData();
        log.info("距离: "+data.getDistance());
        log.info("时长: "+data.getDuration());
        log.info("查询计价规则");
        PriceRule rule=new PriceRule();
        rule.setCityCode("220000");
        rule.setId(10003);
        List<PriceRule> priceRules = priceRuleMapper.selectByAny(rule);
//        List<PriceRule> priceRules = priceRuleMapper.selectById(10000);
//        List<PriceRule> priceRules = priceRuleMapper.selectAll();
//        priceRuleMapper.addPriceRule(new PriceRule(10001,"220000","1",12D,4,3D,2D));
        if(priceRules.size()>=1){
            log.info(priceRules.toString());
        }
        else{
            log.info("未查询到计价规则！");
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }

        log.info("计算价格：");
        ForecastPriceResponse price=new ForecastPriceResponse();
        price.setPrice(201.5);
        return ResponseResult.success(price);
    }
}
