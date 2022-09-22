package com.msb.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Car {
    //
    private 	Integer		id;
    //行政区域
    private 	String		address;
    //车牌号
    private 	String		vehicleNo;
    //车牌颜色： 1：蓝色，2：黄色，3：黑色，4：白色，5：绿色，9：其他
    private 	String		plateColor;
    //座位数
    private 	Integer		seats;
    //品牌
    private 	String		brand;
    //车辆型号
    private 	String		model;
    //车辆类型
    private 	String		vehicleType;
    //车辆所有人
    private 	String		ownerName;
    //车身颜色
    private 	String		vehicleColor;
    //发动机号
    private 	String		engineId;
    //车辆VIN码
    private 	String		vin;
    //车辆注册日期
    private 	LocalDate		certifyDateA;
    //燃料类型： 1：汽油，2：柴油，3：天然气，4：液化气，5：电动，9：其他
    private 	String		fuelType;
    //发动机排量：毫升
    private 	String		engineDisplace;
    //运输证发证机构
    private 	String		transAgency;
    //运营区域
    private 	String		transArea;
    //运输证有效期起
    private 	LocalDate		transDateStart;
    //运输证有效期止
    private 	LocalDate		transDateEnd;
    //初次登记日期
    private 	LocalDate		certifyDateB;
    //车辆检修状态
    private 	String		fixState;
    //下次年检时间
    private 	LocalDate		nextFixDate;
    //年审：0：未年审，1：年审合格，2：年审未合格
    private 	String		checkState;
    //发票打印设备序列号
    private 	String		feePrintId;
    //卫星定位装置品牌
    private 	String		gpsBrand;
    //卫星定位装置型号
    private 	String		gpsModel;
    //卫星定位装置安装日期
    private 	LocalDate		gpsInstallDate;
    //报备日期
    private 	LocalDate		registerDate;
    //1：网络预约出租车，2：巡游出租车，3：私人小客车合乘
    private 	Integer		commercialType;
    //运价类型编码
    private 	String		fareType;
    //0：有效，1：失效
    private 	Integer		state;
    //创建时间
    private     LocalDateTime  gmtCreate;
    //修改时间
    private 	LocalDateTime		gmtModified;
}
