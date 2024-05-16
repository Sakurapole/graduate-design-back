package com.laityh.design.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.common.utils.TransformUtil;
import com.laityh.design.entity.*;
import com.laityh.design.entity.vo.*;
import com.laityh.design.mapper.*;
import com.laityh.design.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectVo, Project> implements IProjectService {
    @Autowired
    private final ProjectStaffMapper projectStaffMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectDeviceMapper projectDeviceMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    public ProjectServiceImpl(ProjectStaffMapper projectStaffMapper) {
        this.projectStaffMapper = projectStaffMapper;
    }

    @Override
    public UserProjectVo getUserProjectInfo() {
        int userId = StpUtil.getLoginIdAsInt();
        List<Map<String, Object>> projectInfoList = projectStaffMapper.executeNativeListSql(
                "select ps.project_id, ps.user_id, ps.department_id, p.project_name, p.project_description, p.project_admin_id, u.user_name as project_admin_name, p.project_status " +
                        "from project_staff ps, project p, user u " +
                        "where ps.user_id = " + userId + " and ps.project_id = p.project_id and p.project_admin_id = u.id order by p.create_time desc limit 6"
        );
        List<ProjectInfoVo> projectInfo = JSON.parseArray(JSON.toJSONString(projectInfoList), ProjectInfoVo.class);
        UserProjectVo userProjectVo = new UserProjectVo();
        userProjectVo.setProjectNum(projectInfo.size());
        userProjectVo.setProjectInfo(projectInfo);
        return userProjectVo;
    }

    @Override
    public PageResultVo<ProjectManagementVo> getProjectListByPagination(ProjectManagementRequestVo pageVo) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.apply("p.project_admin_id = u.id");
        Page<ProjectManagementVo> page = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<ProjectManagementVo> projectManagementInfoVoIPage = projectMapper.selectProjectManagementInfo(page, wrapper);
        PageResultVo<ProjectManagementVo> resultVo = new PageResultVo<>();
        resultVo.setTotal((int) projectManagementInfoVoIPage.getTotal());
        resultVo.setItems(projectManagementInfoVoIPage.getRecords().stream().peek(data -> {
            data.setProjectStatusString(TransformUtil.transformProjectStatusToString(data.getProjectStatus()));
        }).collect(Collectors.toList()));
        return resultVo;
    }

    @Override
    public String updateProjectInfoByAdmin(ProjectManagementVo projectManagementVo) {
        Project project = CopyUtil.copy(projectManagementVo, Project.class);
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("id", project.getProjectAdminId());
        User projectAdmin = userMapper.selectOne(userWrapper);
        project.setProjectAdminId(projectAdmin.getId());
        project.setUpdateTime(LocalDateTime.now());
        return projectMapper.updateById(project) > 0 ? "更新成功" : "更新失败";
    }

    @Override
    public String deleteProjectInfoByAdmin(int projectId) {
        return projectMapper.deleteById(projectId) > 0 ? "删除成功" : "删除失败";
    }

    @Override
    public List<ProjectSelectVo> getAllProject() {
        List<String> roleList = StpUtil.getRoleList();
        int userId = StpUtil.getLoginIdAsInt();
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        if (!roleList.get(0).equals("0")) {
            wrapper.eq("project_admin_id", userId);
        }
        return projectMapper.selectProjectSelectInfo(wrapper);
    }

    @Override
    public ProjectDetailVo getProjectDetail(int projectId) {
        ProjectDetailVo projectDetailVo = projectMapper.selectProjectDetailInfo(projectId);
        projectDetailVo.setProjectStatusName(TransformUtil.transformProjectStatusToString(projectDetailVo.getProjectId()));
        return projectDetailVo;
    }

    @Override
    public String updateProjectStatus(Integer projectId, Integer projectStatus) {
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.set("project_status", projectStatus);
        return projectMapper.update(null, updateWrapper) > 0 ? "success" : "failed";
    }

    @Override
    public PageResultVo<ProjectDeviceVo> getProjectDeviceByPagination(ProjectDeviceRequestVo pageVo) {
        Page<ProjectDevice> devicePage = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<ProjectDeviceVo> page = projectDeviceMapper.selectProjectDeviceVoByPagination(devicePage, pageVo.getProjectId());
        PageResultVo<ProjectDeviceVo> resultVo = new PageResultVo<>();
        resultVo.setTotal((int) page.getTotal());
        resultVo.setItems(page.getRecords().stream().peek(data -> {
            data.setDeviceStatusName(TransformUtil.transformDeviceStatusToString(data.getDeviceStatus()));
        }).collect(Collectors.toList()));
        return resultVo;
    }

    @Override
    public String updateProjectDeviceInfoById(int projectDeviceId, int deviceStatus) {
        UpdateWrapper<ProjectDevice> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_device_id", projectDeviceId);
        updateWrapper.set("device_status", deviceStatus);
        return projectDeviceMapper.update(null, updateWrapper) > 0 ? "success" : "failed";
    }

    @Override
    public String deleteProjectDeviceById(int projectDeviceId) {
        return projectDeviceMapper.deleteById(projectDeviceId) > 0 ? "success" : "failed";
    }

    @Override
    public String insertProjectDevice(int projectId, int deviceId) {
        Device device = deviceMapper.selectById(deviceId);
        if (device.getCurrentNumber() <= 0) {
            return "设备数量不足";
        }
        ProjectDevice projectDevice = new ProjectDevice();
        projectDevice.setDeviceStatus(0);
        projectDevice.setProjectId(projectId);
        projectDevice.setDeviceId(deviceId);
        projectDevice.setCreateTime(LocalDateTime.now());
        projectDevice.setUpdateTime(LocalDateTime.now());
        if (projectDeviceMapper.insert(projectDevice) > 0) {
            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("device_id", deviceId);
            updateWrapper.set("current_number", device.getCurrentNumber() - 1);
            deviceMapper.update(null, updateWrapper);
            return "success";
        } else {
            return "添加设备失败";
        }
    }
}
