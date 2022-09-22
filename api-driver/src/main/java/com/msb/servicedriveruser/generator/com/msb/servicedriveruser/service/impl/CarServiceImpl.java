package com.msb.servicedriveruser.service.impl;

import com.msb.servicedriveruser.entity.Car;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆信息 服务实现类
 * </p>
 *
 * @author juke
 * @since 2022-09-21
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

}
