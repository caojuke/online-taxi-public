package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {
    public ResponseResult driving(String depLongtitude, String depLatitude, String destLongtitude, String destLatitude){
        log.info("调用map服务，得到距离和时间");
        log.info("读取计价规则");
        DirectionResponse directionResponse=new DirectionResponse();
        directionResponse.setDistance(55);
        directionResponse.setDuration(70);
        return ResponseResult.success(directionResponse);
    }
}
