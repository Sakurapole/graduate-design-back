package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @TableId(value = "schedule_id", type = IdType.AUTO)
    private Integer scheduleId;

    @TableField(value = "project_id")
    private Integer projectId;

    @TableField(value = "first_schedule_description")
    private String firstScheduleDescription;

    @TableField(value = "second_schedule_description")
    private String secondScheduleDescription;

    @TableField(value = "third_schedule_description")
    private String thirdScheduleDescription;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
