package com.msb.serviceprice.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

    public ResponseResult forecastPrice(String depLongtitude, String depLatitude, String destLongtitude, String destLatitude){
        log.info("调用map服务，得到距离和时间");
        log.info("读取计价规则");
        ForecastPriceResponse price=new ForecastPriceResponse();
        price.setPrice(200L);
        return ResponseResult.success(price);
    }
}
