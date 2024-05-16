package com.laityh.design.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.laityh.design.entity.vo.*;
import com.laityh.design.service.IProjectService;
import com.laityh.design.service.impl.ProjectDocumentServiceImpl;
import com.laityh.design.service.impl.ProjectServiceImpl;
import com.laityh.design.service.impl.ProjectStaffServiceImpl;
import com.laityh.design.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @Autowired
    private ProjectDocumentServiceImpl projectDocumentService;

    @Autowired
    private ProjectStaffServiceImpl projectStaffService;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @PostMapping("/getAllProject")
    public List<ProjectSelectVo> getAllProject() {
        return projectService.getAllProject();
    }

    @PostMapping("/getUserProjectInfo")
    public UserProjectVo getUserProjectInfo() {
        return projectService.getUserProjectInfo();
    }

    @PostMapping("/getProjectListByPagination")
    public PageResultVo<ProjectManagementVo> getProjectListByPagination(@RequestBody ProjectManagementRequestVo pageVo) {
        return projectService.getProjectListByPagination(pageVo);
    }

    @PostMapping("/updateProjectInfoByAdmin")
    public String updateProjectInfoByAdmin(@RequestBody ProjectManagementVo projectManagementVo) {
        return projectService.updateProjectInfoByAdmin(projectManagementVo);
    }

    @PostMapping("deleteProjectInfoByAdmin")
    public String deleteProjectInfoByAdmin(@RequestBody Map<String, Object> map) {
        return projectService.deleteProjectInfoByAdmin(Integer.parseInt(map.get("projectId").toString()));
    }

    @PostMapping("/getProjectStaffByPagination")
    public PageResultVo<UserManagementInfoVo> getProjectStaffByPagination(@RequestBody ProjectStaffRequestVo pageVo) {
        return projectStaffService.getProjectStaffByPagination(pageVo);
    }

    @PostMapping("/getUserInfoByPaginationAndNotInProject")
    public PageResultVo<UserManagementInfoVo> getUserInfoByPaginationAndNotInProject(@RequestBody ProjectStaffRequestVo pageVo) {
        return projectStaffService.getUserInfoByPaginationAndNotInProject(pageVo);
    }

    @PostMapping("/addProjectStaff")
    public String addProjectStaff(@RequestBody AddProjectStaffVo addProjectStaffVo) {
        return projectStaffService.addProjectStaff(addProjectStaffVo);
    }

    @PostMapping("/getProjectDetail")
    public ProjectDetailVo getProjectDetail(@RequestBody Map<String, Object> map) {
        return projectService.getProjectDetail(Integer.parseInt(map.get("projectId").toString()));
    }

    @PostMapping("/updateProjectScheduleInfo")
    public String updateProjectScheduleInfo(@RequestBody Map<String, Object> map) {
        return scheduleService.updateProjectScheduleInfo(map);
    }

    @PostMapping("/updateProjectStatus")
    public String updateProjectStatus(@RequestBody Map<String, Object> map) {
        return projectService.updateProjectStatus(
                Integer.parseInt(map.get("projectId").toString()),
                Integer.parseInt(map.get("projectStatus").toString())
        );
    }

    @PostMapping("/getProjectDocumentDetailByPagination")
    public PageResultVo<ProjectDocumentDetailVo> getProjectDocumentDetailByPagination(@RequestBody ProjectDocumentDetailRequestVo pageVo) {
        return projectDocumentService.getProjectDocumentDetailByPagination(pageVo);
    }

    @PostMapping("/uploadDocumentFile")
    public String uploadDocumentFile(ProjectDocumentUploadVo projectDocumentUploadVo) throws IOException {
        System.out.println(projectDocumentUploadVo);
        return projectDocumentService.uploadDocumentFile(projectDocumentUploadVo);
    }

    @PostMapping("/deleteDocumentFile")
    public String deleteDocumentFile(@RequestBody Map<String, Object> map) {
        return projectDocumentService.deleteDocumentFile(Integer.parseInt(map.get("documentId").toString()));
    }

    @PostMapping("/downloadDocumentFile")
    public ResponseEntity<Resource> downloadDocumentFile(@RequestBody Map<String, Object> map) throws IOException {
        return projectDocumentService.downloadDocumentFile(Integer.parseInt(map.get("documentId").toString()));
    }

    @PostMapping("/getProjectDeviceByPagination")
    public PageResultVo<ProjectDeviceVo> getProjectDeviceByPagination(@RequestBody ProjectDeviceRequestVo pageVo) {
        return projectService.getProjectDeviceByPagination(pageVo);
    }

    @PostMapping("/updateProjectDeviceInfoById")
    public String updateProjectDeviceInfoById(@RequestBody Map<String, Object> map) {
        return projectService.updateProjectDeviceInfoById(
                Integer.parseInt(map.get("projectDeviceId").toString()),
                Integer.parseInt(map.get("~").toString())
        );
    }

    @PostMapping("/deleteProjectDeviceById")
    public String deleteProjectDeviceById(@RequestBody Map<String, Object> map) {
        return projectService.deleteProjectDeviceById(Integer.parseInt(map.get("projectDeviceId").toString()));
    }

    @PostMapping("/insertProjectDevice")
    public String insertProjectDevice(@RequestBody Map<String, Object> map) {
        return projectService.insertProjectDevice(
                Integer.parseInt(map.get("projectId").toString()),
                Integer.parseInt(map.get("deviceId").toString())
        );
    };
}


