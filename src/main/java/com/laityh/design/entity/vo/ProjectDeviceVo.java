package com.laityh.design.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDeviceVo {
    private Integer projectDeviceId;
    private Integer projectId;
    private String projectName;
    private Integer deviceId;
    private String deviceName;
    private String deviceKind;
    private Integer deviceTypeId;
    private Integer deviceStatus;
    private String deviceStatusName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime updateTime;
}
