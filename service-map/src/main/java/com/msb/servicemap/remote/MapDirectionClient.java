package com.msb.servicemap.remote;

import com.msb.internalcommon.constant.MapConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DirectionResponse;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDirectionClient {
    @Value("${map.address}")
    String mapAddress;
    @Value("${map.key}")
    String mapKey;
    @Autowired
    RestTemplate restTemplate;
    public DirectionResponse driving(String depLongtitude, String depLatitude, String destLongtitude, String destLatitude){
        log.info("MapDirectionClient调用高德地图map服务，得到距离和时间");
        //调用第三方地图接口,拼接url
        StringBuilder urlBuild=new StringBuilder();
        urlBuild.append(mapAddress);
        urlBuild.append("origin="+depLongtitude);
        urlBuild.append(","+depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination="+destLongtitude);
        urlBuild.append(","+destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=base");
        urlBuild.append("&");
        urlBuild.append("output=json");
        urlBuild.append("&");
        urlBuild.append("key="+mapKey);
        //此处调用了高德地图的接口！！！！！！！！！
        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String body = forEntity.getBody();

        log.info("url="+urlBuild);
        log.info("data from gaode="+body);
        DirectionResponse directionResponse = parseDirectionEntity(body);
        log.info("distance= "+directionResponse.getDistance());
        log.info("duration= "+directionResponse.getDuration());

        return directionResponse;
    }

    /**
     * 解析导航返回的json字符串
     * @param directionString
     * @return
     */

    private DirectionResponse parseDirectionEntity(String directionString){

        DirectionResponse directionResponse=null;
        try {
            JSONObject jsonObject = JSONObject.fromObject(directionString);
            if(jsonObject.has(MapConstant.STATUS)){
                int status = jsonObject.getInt(MapConstant.STATUS);
                if(status==1)
                    if(jsonObject.has(MapConstant.ROUTE)){
                        JSONObject routeObject = jsonObject.getJSONObject(MapConstant.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(MapConstant.PATHS);
                        JSONObject pathObject0 = pathsArray.getJSONObject(0);
                        directionResponse=new DirectionResponse();
                        if(pathObject0.has(MapConstant.DISTANCE)){
                            directionResponse.setDistance(pathObject0.getInt(MapConstant.DISTANCE));
                        }
                        if(pathObject0.has(MapConstant.DURATION)){
                            directionResponse.setDuration(pathObject0.getInt(MapConstant.DURATION));
                        }
                    }
            }
        }catch (Exception e){
            log.error("fail to parse the String!");
        }
        return directionResponse;
    }
}
