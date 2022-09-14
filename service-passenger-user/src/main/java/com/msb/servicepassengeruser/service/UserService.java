package com.msb.servicepassengeruser.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.PassengerUser;
import com.msb.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("user service被调用，手机号："+passengerPhone);

        //在数据库按手机号查询用户
        Map<String,Object> map=new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers=passengerUserMapper.selectByMap(map);

        //判断用户信息是否存在
        if(passengerUsers.size()==0){
            PassengerUser passengerUser=new PassengerUser();
            passengerUser.setPassengerName("曹居科");
            passengerUser.setPassengerGender((byte)0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte)0);
            LocalDateTime now=LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);
            System.out.println("增加新用户："+passengerPhone);
        }
        else{
            System.out.println("请求来自用户："+passengerUsers.get(0).getPassengerName());
        }
        //返回值
        return ResponseResult.success("--");
    }
}






















































