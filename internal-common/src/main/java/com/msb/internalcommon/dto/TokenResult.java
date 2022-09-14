package com.msb.internalcommon.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenResult {
    private String phone;
    private String identity;
    private String tokenType;
}
