package com.msb.internalcommon.dto;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DriverCarBindingRelationship {
    // bigint, 主键
    private 	Integer		id;
    // bigint, 司机ID
    private 	Integer		driverId;
    // bigint, 车辆ID
    private 	Integer		carId;
    // int, 1:绑定，2：解绑
    private 	Integer		bindState;
    // datetime, 绑定时间
    private 	LocalDateTime		bindingTime;
    // datetime, 解绑时间
    private     LocalDateTime unbindingTime;
}
