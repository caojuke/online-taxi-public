package com.msb.internalcommon.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicDistrict implements Serializable {
   private String addressCode;
   private String addressName;
   private String parentAddressCode;
   private Integer addressLevel;
}
