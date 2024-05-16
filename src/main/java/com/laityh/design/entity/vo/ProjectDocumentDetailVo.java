package com.laityh.design.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDocumentDetailVo {
    private Integer documentId;
    private String documentName;
    private Integer projectId;
    private Integer userId;
    private String projectName;
    private Integer documentNumberSchedule;
    private String documentProjectSchedule;
    private String uploadUserName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime createTime;
}
