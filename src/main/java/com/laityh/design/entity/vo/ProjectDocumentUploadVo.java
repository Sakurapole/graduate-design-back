package com.laityh.design.entity.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProjectDocumentUploadVo {
    private Integer projectId;
    private MultipartFile[] file;
}
