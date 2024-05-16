package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author laityh
 * @since 2024-04-17
 */
@Data
@TableName("project")
public class Project {
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;

    @TableField(value = "project_name")
    private String projectName;

    @TableField(value = "project_description")
    private String projectDescription;

    @TableField(value = "project_admin_id")
    private Integer projectAdminId;

    @TableField(value = "project_status")
    private Integer projectStatus;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
