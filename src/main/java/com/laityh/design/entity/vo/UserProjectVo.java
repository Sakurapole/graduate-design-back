package com.laityh.design.entity.vo;

import com.laityh.design.entity.ProjectStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class UserProjectVo {
    private Integer projectNum;
    private List<ProjectInfoVo> projectInfo;
}