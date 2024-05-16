package com.laityh.design.entity.vo;

import lombok.Data;

@Data
public class ProjectStaffRequestVo {
    private Integer projectId;
    private Integer pageSize;
    private Integer page;
}
