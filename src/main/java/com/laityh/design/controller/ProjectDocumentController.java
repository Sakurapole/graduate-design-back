package com.laityh.design.controller;

import com.laityh.design.entity.vo.DailyDocumentNumVo;
import com.laityh.design.service.impl.ProjectDocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project/document")
public class ProjectDocumentController {
    @Autowired
    private ProjectDocumentServiceImpl projectDocumentService;

    @PostMapping("/getDailyDocumentNum")
    public List<DailyDocumentNumVo> getDailyDocumentNum() {
        return projectDocumentService.getDailyDocumentNum();
    }
}
