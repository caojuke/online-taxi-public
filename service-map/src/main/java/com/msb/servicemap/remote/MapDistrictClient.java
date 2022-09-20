package com.msb.servicemap.remote;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class MapDistrictClient {

    @Value("${map.district}")
    String mapDistrict;
    @Value("${map.key}")
    String mapKey;
    @Autowired
    RestTemplate restTemplate;
    //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
    public String initDicDistrict(String keywords){

        StringBuilder url=new StringBuilder();
        url.append(mapDistrict);
        url.append("keywords="+keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key="+mapKey);
        System.out.println(url);
        //此处调用了高德地图的接口！！！！！！！！！
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        String result = forEntity.getBody();
        return result;
    }

}
