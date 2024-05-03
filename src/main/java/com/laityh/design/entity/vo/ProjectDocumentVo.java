package com.laityh.design.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDocumentVo {
    private Integer documentId;
    private String documentName;
    private String documentPath;
    private String documentNumberSchedule;
    private Integer userId;
    private Integer projectId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
