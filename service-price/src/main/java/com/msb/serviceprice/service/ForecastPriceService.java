package com.msb.serviceprice.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.msb.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    ServiceMapClient serviceMapClient;
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO){
        log.info("调用map服务，得到距离和时间");
        ResponseResult result = serviceMapClient.driving(forecastPriceDTO);
        log.info("result: "+result.getData().toString());


        log.info("计算价格");
        ForecastPriceResponse price=new ForecastPriceResponse();
        price.setPrice(201.5);
        return ResponseResult.success(price);
    }
}
