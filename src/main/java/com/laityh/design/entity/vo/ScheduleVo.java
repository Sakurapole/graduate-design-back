package com.laityh.design.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVo {
    private Integer scheduleId;
    private Integer projectId;
    private String firstScheduleDescription;
    private String secondScheduleDescription;
    private String thirdScheduleDescription;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
