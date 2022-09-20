package com.msb.serviceprice.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.dto.PriceRule;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.msb.internalcommon.util.BigDecimalUtil;
import com.msb.serviceprice.mapper.PriceRuleMapper;
import com.msb.serviceprice.remote.ServiceMapClient;
import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        log.info("距离: "+data.getDistance()+"米");
        log.info("时长: "+data.getDuration()+"秒");
        log.info("查询计价规则");
        PriceRule rule=new PriceRule();
        rule.setCityCode("110000");
        rule.setId(10000);
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
        Double price = getPrice(data.getDistance(), data.getDuration(), priceRules.get(0));
        log.info("计算价格："+price +" 元.");
        //封装一下子
        ForecastPriceResponse priceResponse=new ForecastPriceResponse();
        priceResponse.setPrice(price);
        return ResponseResult.success(priceResponse);
    }

    /**
     * 计算价格=起步价+里程费+时长费
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return 价格返回
     */
    private Double getPrice(Integer distance,Integer duration,PriceRule priceRule){
        double price=0;
        //起步价
        Double startFare=priceRule.getStartFare();
        price= BigDecimalUtil.add(price,startFare);

        //起步里程
        Integer startMile=priceRule.getStartMile();
        //实际里程-起步里程
        double distanceIncre = BigDecimalUtil.subtract(BigDecimalUtil.divide(distance,1000d),startMile);
        Double mile=distanceIncre<0 ? 0 : distanceIncre;
        //里程单价
        double pricePerMile = priceRule.getUnitPricePerMile();
        price=BigDecimalUtil.add(price,BigDecimalUtil.multiply(pricePerMile,mile));

        //时长费计算,时长*每分钟单价
        Double pricePerMinute= priceRule.getUnitPricePerMinute();
        double durationFare = BigDecimalUtil.multiply(BigDecimalUtil.divide(duration, 60), pricePerMinute);

        //总价
        price=BigDecimalUtil.add(price,durationFare);

        return price;
    }
}
