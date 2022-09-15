package com.msb.apipassenger.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    /**
     * 计算预估价格
     * @param depLongtitude 出发地经度
     * @param depLatitude 出发地纬度
     * @param destLongtitude 目的地经度
     * @param destLatitude 目的地纬度
     * @return 返回结果
     */
    public ResponseResult forecastPrice(String depLongtitude,String depLatitude,String destLongtitude,String destLatitude){
        log.info("调用计价服务，计算价格");
        ForecastPriceResponse price=new ForecastPriceResponse();
        price.setPrice(200L);
        return ResponseResult.success(price);
    }
}
