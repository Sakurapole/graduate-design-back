package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("project_device")
public class ProjectDevice {
    @TableId(value = "project_device_id", type = IdType.AUTO)
    private Integer projectDeviceId;
    @TableField(value = "project_id")
    private Integer projectId;
    @TableField(value = "device_id")
    private Integer deviceId;
    @TableField(value = "device_status")
    private Integer deviceStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
