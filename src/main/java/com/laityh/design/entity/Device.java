package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("device")
public class Device {
    @TableId(value = "device_id", type = IdType.AUTO)
    private Integer deviceId;
    @TableField(value = "device_type")
    private Integer deviceType;
    @TableField(value = "device_name")
    private String deviceName;
    @TableField(value = "device_description")
    private String deviceDescription;
    @TableField(value = "current_number")
    private Integer currentNumber;
    @TableField(value = "total_number")
    private Integer totalNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
