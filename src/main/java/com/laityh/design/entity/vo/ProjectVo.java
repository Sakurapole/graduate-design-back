package com.laityh.design.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVo {
    private Integer projectId;
    private String projectName;
    private String projectDescription;
    private Integer projectAdminId;
    private Integer projectStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
