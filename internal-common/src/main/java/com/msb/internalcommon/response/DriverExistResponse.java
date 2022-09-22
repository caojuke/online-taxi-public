package com.msb.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverExistResponse  implements Serializable {
    private String driverPhone;
    private int isExist;
}
