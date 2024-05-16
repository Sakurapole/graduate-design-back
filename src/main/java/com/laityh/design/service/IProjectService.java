package com.laityh.design.service;

import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.Project;
import com.laityh.design.entity.vo.*;

import java.util.List;
import java.util.Map;

public interface IProjectService extends IBaseService<ProjectVo, Project> {
    public UserProjectVo getUserProjectInfo();

    PageResultVo<ProjectManagementVo> getProjectListByPagination(ProjectManagementRequestVo pageVo);

    String updateProjectInfoByAdmin(ProjectManagementVo projectManagementVo);

    String deleteProjectInfoByAdmin(int projectId);

    List<ProjectSelectVo> getAllProject();

    ProjectDetailVo getProjectDetail(int projectId);

    String updateProjectStatus(Integer projectId, Integer projectStatus);

    PageResultVo<ProjectDeviceVo> getProjectDeviceByPagination(ProjectDeviceRequestVo pageVo);

    String updateProjectDeviceInfoById(int projectDeviceId, int deviceStatus);

    String deleteProjectDeviceById(int projectDeviceId);

    String insertProjectDevice(int projectId, int deviceId);
}
