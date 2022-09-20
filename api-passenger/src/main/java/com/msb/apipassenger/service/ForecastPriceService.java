package com.msb.apipassenger.service;

import com.msb.apipassenger.remote.ServicePriceClient;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    ServicePriceClient servicePriceClient;
    /**
     * 计算预估价格
     * @param forecastPriceDTO 包含出发地和目的地的经纬度
     * @return 返回结果
     */
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO){
        log.info("调用计价服务，计算价格");
        ResponseResult<ForecastPriceResponse> priceResult = servicePriceClient.forecastPrice(forecastPriceDTO);
        //封装一下子
        ForecastPriceResponse priceResponse=new ForecastPriceResponse();
        priceResponse.setPrice(priceResult.getData().getPrice());
        return ResponseResult.success(priceResponse);
    }
}
