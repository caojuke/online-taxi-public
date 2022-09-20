package com.msb.servicemap.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.MapConstant;
import com.msb.internalcommon.dto.DicDistrict;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicemap.mapper.DistrictMapper;
import com.msb.servicemap.remote.MapDistrictClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DicDistrictService {
    @Autowired
    MapDistrictClient mapDistrictClient;
    @Autowired
    DistrictMapper districtMapper;
    /*
    * 实例:https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
    */
    public ResponseResult initDicDistrict(String keywords){
        //请求地图行政区域查询
        String result = mapDistrictClient.initDicDistrict(keywords);
        //解析
        JSONObject districtJsonObject=JSONObject.fromObject(result);
        int status=districtJsonObject.getInt(MapConstant.STATUS);
        if(status!=1){
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(),CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        //得到最大一级的districts
        JSONArray districts = districtJsonObject.getJSONArray(MapConstant.DISTRICTS);
        //
        searchJsonTree(districts,"0");


        return ResponseResult.success(result);
    }

    /**
     * 递归方法，每一次都遍历一个json数组，数组元素是一个distirct，每个元素包含一个json数组，得到后递归调用！！！
     * @param districts json数组，是district的集合。
     * @param parentAdcode 注意，第一层的json数组里每个district的父district是需要调用者指定的！！！因此需要此参数传入。
     */
    private void searchJsonTree(JSONArray districts,String parentAdcode){

        for (int i = 0; i < districts.size(); i++) {
            //取出数组元素
            JSONObject currentDistrict=districts.getJSONObject(i);

            //组装一个district对象,存入数据库
            String adcode = (String)currentDistrict.get("adcode");
            String name = (String)currentDistrict.get("name");
            String level = (String)currentDistrict.get("level");
            //跳过“street”，不保存街道入库
            if ("street".equals(level))continue;
            int levelNumber=convertToNumber(level);
            DicDistrict district=new DicDistrict(adcode,name,parentAdcode,levelNumber);

            //存入数据库
            try {
                districtMapper.addDistrict(district);
                log.info(district+" 加入数据库成功");
            }catch (Exception exception){
                log.info("数据库操作失败： " + exception.getMessage());
            }

            //取出当前区域的districtsJson数组，进行递归检索
            JSONArray childDistricts = currentDistrict.getJSONArray("districts");
            if(childDistricts.size()>0){
                searchJsonTree(childDistricts,adcode);
            }
            else {
                //进入到下一个 district
                continue;
            }

        }
    }
    private int convertToNumber(String level){
            int levelNumber=0;
            if(level.trim().equals("country")){
                levelNumber=0;
            }else if(level.trim().equals("province")){
                levelNumber=1;
            }else if(level.trim().equals("city")){
                levelNumber=2;
            }else if(level.trim().equals("district")){
                levelNumber=3;
            }else if(level.trim().equals("street")){
                levelNumber=4;
            }else {
                levelNumber=-1;
            }
        return levelNumber;
    }

}
