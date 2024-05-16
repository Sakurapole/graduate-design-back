package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("project_staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStaff {
    @TableId(value = "project_id", type = IdType.NONE)
    private Integer projectId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "department_id")
    private Integer departmentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
