package com.laityh.design.entity.vo;

import lombok.Data;

@Data
public class DeviceInfoUpdateVo {
    private Integer deviceId;
    private Integer deviceType;
    private String deviceName;
    private String deviceDescription;
    private Integer currentNumber;
    private Integer totalNumber;
}
