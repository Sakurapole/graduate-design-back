package com.laityh.design.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStaffVo {
    private Integer projectId;
    private Integer userId;
    private Integer departmentId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
