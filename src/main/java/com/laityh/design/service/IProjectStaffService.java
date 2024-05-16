package com.laityh.design.service;

import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.ProjectStaff;
import com.laityh.design.entity.vo.*;

public interface IProjectStaffService extends IBaseService<ProjectStaffVo, ProjectStaff> {
    PageResultVo<UserManagementInfoVo> getProjectStaffByPagination(ProjectStaffRequestVo pageVo);

    String addProjectStaff(AddProjectStaffVo addProjectStaffVo);

    PageResultVo<UserManagementInfoVo> getUserInfoByPaginationAndNotInProject(ProjectStaffRequestVo pageVo);
}
