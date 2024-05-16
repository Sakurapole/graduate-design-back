package com.laityh.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.entity.ProjectStaff;
import com.laityh.design.entity.User;
import com.laityh.design.entity.vo.*;
import com.laityh.design.mapper.ProjectStaffMapper;
import com.laityh.design.mapper.UserMapper;
import com.laityh.design.service.IProjectStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectStaffServiceImpl extends BaseServiceImpl<ProjectStaffVo, ProjectStaff> implements IProjectStaffService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectStaffMapper projectStaffMapper;

    @Override
    public PageResultVo<UserManagementInfoVo> getProjectStaffByPagination(ProjectStaffRequestVo pageVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("u.role_id = ur.role_id and u.department_id = d.department_id and p.project_id = ps.project_id and u.id = ps.user_id");
        wrapper.eq("p.project_id", pageVo.getProjectId());
        Page<UserManagementInfoVo> page = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<UserManagementInfoVo> userManagementInfoVoIPage = userMapper.selectUserManagementInfoByProject(page, wrapper);
        PageResultVo<UserManagementInfoVo> resultVo = new PageResultVo<>();
        resultVo.setTotal((int) userManagementInfoVoIPage.getTotal());
        resultVo.setItems(userManagementInfoVoIPage.getRecords());
        return resultVo;
    }

    @Override
    public String addProjectStaff(AddProjectStaffVo addProjectStaffVo) {
        ProjectStaff staff = CopyUtil.copy(addProjectStaffVo, ProjectStaff.class);
        staff.setCreateTime(LocalDateTime.now());
        staff.setUpdateTime(LocalDateTime.now());
        return projectStaffMapper.insert(staff) > 0 ? "添加成功" : "添加失败";
    }

    @Override
    public PageResultVo<UserManagementInfoVo> getUserInfoByPaginationAndNotInProject(ProjectStaffRequestVo pageVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("u.role_id = ur.role_id and u.department_id = d.department_id and p.project_id = ps.project_id and u.id not in (select user_id from project_staff where project_id = " +pageVo.getProjectId()+")");
        wrapper.eq("p.project_id", pageVo.getProjectId());
        Page<UserManagementInfoVo> page = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<UserManagementInfoVo> userManagementInfoVoIPage = userMapper.selectUserManagementInfoByProject(page, wrapper);
        PageResultVo<UserManagementInfoVo> resultVo = new PageResultVo<>();
        resultVo.setTotal((int) userManagementInfoVoIPage.getTotal());
        resultVo.setItems(userManagementInfoVoIPage.getRecords());
        return resultVo;
    }
}
