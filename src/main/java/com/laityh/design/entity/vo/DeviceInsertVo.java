package com.laityh.design.entity.vo;

import lombok.Data;

@Data
public class DeviceInsertVo {
    private Integer deviceType;
    private String deviceName;
    private String deviceDescription;
    private Integer totalNumber;
}
