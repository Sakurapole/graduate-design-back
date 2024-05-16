package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

        import java.time.LocalDateTime;

/**
* @author laityh
* @since 2024-04-17
*/
@Data
@TableName("department")
public class Department {
    @TableId(value = "department_id", type = IdType.AUTO)
    private Integer departmentId;

    @TableField(value = "department_name")
    private String departmentName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
