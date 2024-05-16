package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("project_document")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDocument {
    @TableId(value = "document_id", type = IdType.AUTO)
    private Integer documentId;

    @TableField(value = "document_name")
    private String documentName;

    @TableField(value = "document_path")
    private String documentPath;

    @TableField(value = "document_number_schedule")
    private Integer documentNumberSchedule;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "project_id")
    private Integer projectId;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
