package com.laityh.design.entity.vo;

import lombok.Data;

@Data
public class ProjectInfoVo {
    Integer projectId;
    Integer userId;
    Integer departmentId;
    String projectName;
    String projectDescription;
    String projectAdminId;
    String projectAdminName;
    Integer projectStatus;
}
