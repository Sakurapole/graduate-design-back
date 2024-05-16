package com.laityh.design.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectManagementVo {
    private Integer projectId;
    private String projectName;
    private String projectDescription;
    private Integer projectAdminId;
    private String projectAdminName;
    private Integer projectStatus;
    private String projectStatusString;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime updateTime;
}
