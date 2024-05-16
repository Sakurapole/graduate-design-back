package com.laityh.design.service;

import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.ProjectDocument;
import com.laityh.design.entity.vo.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface IProjectDocumentService extends IBaseService<ProjectDocumentVo, ProjectDocument> {
    public List<DailyDocumentNumVo> getDailyDocumentNum();

    PageResultVo<ProjectDocumentDetailVo> getProjectDocumentDetailByPagination(ProjectDocumentDetailRequestVo pageVo);

    String uploadDocumentFile(ProjectDocumentUploadVo projectDocumentUploadVo) throws IOException;

    String deleteDocumentFile(int documentId);

    ResponseEntity<Resource> downloadDocumentFile(int documentId) throws IOException;
}
