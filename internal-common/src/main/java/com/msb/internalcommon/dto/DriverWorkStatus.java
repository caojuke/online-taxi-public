package com.msb.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverWorkStatus implements Serializable {
    // bigint, 司机ID
    private 	Long		driverId;
    // int, 当前工作状态 0:STOP, 1:START , 2:PAUSED
    private 	Integer		workStatus;
    // datetime, 创建时间
    private 	LocalDateTime		gmtCreate;
    // datetime, 修改时间
    private LocalDateTime gmtModified;
}
