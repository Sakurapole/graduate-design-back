package com.laityh.design.controller;

import com.laityh.design.service.impl.ProjectDocumentServiceImpl;
import com.laityh.design.service.impl.ProjectServiceImpl;
import com.laityh.design.service.impl.ProjectStaffServiceImpl;
import com.laityh.design.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private ProjectDocumentServiceImpl projectDocumentService;

    @Autowired
    private ProjectStaffServiceImpl projectStaffService;

    @Autowired
    private ScheduleServiceImpl scheduleService;
}


