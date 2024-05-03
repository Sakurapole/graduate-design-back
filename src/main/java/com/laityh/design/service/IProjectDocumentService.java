package com.laityh.design.service;

import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.ProjectDocument;
import com.laityh.design.entity.vo.DailyDocumentNumVo;
import com.laityh.design.entity.vo.ProjectDocumentVo;

import java.util.List;

public interface IProjectDocumentService extends IBaseService<ProjectDocumentVo, ProjectDocument> {
    public List<DailyDocumentNumVo> getDailyDocumentNum();
}
