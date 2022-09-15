package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.msb.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {
    @Autowired
    MapDirectionClient mapDirectionClient;
    public ResponseResult driving(String depLongtitude, String depLatitude, String destLongtitude, String destLatitude){
        log.info("DirectionService调用map服务，得到距离和时间");
        //调用第三方地图接口
        DirectionResponse response = mapDirectionClient.driving(depLongtitude, depLatitude, destLongtitude, destLatitude);
        return ResponseResult.success(response);
    }
}
