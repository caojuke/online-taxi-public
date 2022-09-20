package com.msb.apipassenger.controller;

import com.msb.apipassenger.service.ForecastPriceService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastPriceController {
    @Autowired
    ForecastPriceService forecastPriceService;
    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){

        log.info(forecastPriceDTO.toString());
        return forecastPriceService.forecastPrice(forecastPriceDTO);
    }
}
