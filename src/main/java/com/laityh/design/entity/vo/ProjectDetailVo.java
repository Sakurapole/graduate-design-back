package com.laityh.design.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDetailVo {
    private Integer projectId;
    private Integer scheduleId;
    private String projectName;
    private String projectDescription;
    private Integer projectAdminId;
    private String projectAdminName;
    private Integer projectStatus;
    private String projectStatusName;
    private String firstScheduleDescription;
    private String secondScheduleDescription;
    private String thirdScheduleDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime projectCreateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime scheduleUpdateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime scheduleCreateTime;
}
