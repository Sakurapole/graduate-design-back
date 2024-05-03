package com.laityh.design.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laityh.design.common.exceptions.CustomException;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.entity.*;
import com.laityh.design.entity.vo.LoginUserVo;
import com.laityh.design.entity.vo.UserHomeCardInfoVo;
import com.laityh.design.entity.vo.UserVo;
import com.laityh.design.mapper.*;
import com.laityh.design.service.IUserService;
import com.laityh.design.common.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
* @author laityh
* @since 2024-04-17
*/
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserVo, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectDocumentMapper projectDocumentMapper;
    @Autowired
    private ProjectStaffMapper projectStaffMapper;

    public List<UserVo> getAllUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        List<User> users = userMapper.selectList(wrapper);
        List<UserVo> userList =  new ArrayList<>();
        users.forEach(user -> {
            UserVo userVo = CopyUtil.copy(user, UserVo.class);
            userList.add(userVo);
        });
        return userList;
    }

    @Override
    public User validateLogin(LoginUserVo loginUserVo) throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", loginUserVo.getUserName());
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new CustomException("用户不存在");
        }
        if (loginUserVo.getPassword().equals(user.getPassword())) {
            return user;
        } else {
            throw new CustomException("密码错误");
        }
    }

    @Override
    public UserVo getUserInfo() {
        int userId = StpUtil.getLoginIdAsInt();
//        log.info(userMapper.getUserInfo(userId).toString());
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", userId);
//        User user = userMapper.selectOne(wrapper);
//        UserVo userVo = CopyUtil.copy(user, UserVo.class);
        UserVo userVo = userMapper.getUserInfo(userId);
        userVo.setTokenInfo(StpUtil.getTokenInfo());
        return userVo;
    }

    @Override
    public List<UserHomeCardInfoVo> getUserHomeCardInfo() {
        List<UserHomeCardInfoVo> list = new ArrayList<>();
        UserVo currentUser = getUserInfo();
        int roleId = currentUser.getRoleId();
        QueryWrapper<Department> queryDepartmentWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        QueryWrapper<ProjectDocument> projectDocumentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<ProjectStaff> projectStaffQueryWrapper = new QueryWrapper<>();
        if (roleId == 0) {
            int departmentNum = departmentMapper.selectCount(queryDepartmentWrapper).intValue();
            int userNum = userMapper.selectCount(userQueryWrapper).intValue();
            int projectNum = projectMapper.selectCount(projectQueryWrapper).intValue();
            int projectDocumentNum = projectDocumentMapper.selectCount(projectDocumentQueryWrapper).intValue();
            list.add(new UserHomeCardInfoVo("部门数", String.valueOf(departmentNum)));
            list.add(new UserHomeCardInfoVo("用户数", String.valueOf(userNum)));
            list.add(new UserHomeCardInfoVo("项目数", String.valueOf(projectNum)));
            list.add(new UserHomeCardInfoVo("项目文件数", String.valueOf(projectDocumentNum)));
        } else if (roleId == 1) {
            userQueryWrapper.eq("department_id", currentUser.getDepartmentId());
            int departmentDocument = projectDocumentMapper.executeNativeCountSql(
                    "select count(*) from department d, project_document pd, user u " +
                            "where d.department_id = u.department_id and u.id = pd.user_id;"
            );
            list.add(new UserHomeCardInfoVo("当前部门", String.valueOf(currentUser.getDepartmentName())));
            list.add(new UserHomeCardInfoVo("部门下用户总数", String.valueOf(userMapper.selectCount(userQueryWrapper).intValue())));
            list.add(new UserHomeCardInfoVo("总项目数", String.valueOf(projectMapper.selectCount(projectQueryWrapper))));
            list.add(new UserHomeCardInfoVo("部门上传文件数", String.valueOf(departmentDocument)));
        } else {
            projectStaffQueryWrapper.eq("user_id", StpUtil.getLoginId());
            projectDocumentQueryWrapper.eq("user_id", StpUtil.getLoginId());
            userQueryWrapper.eq("role_id", 1);
            userQueryWrapper.eq("department_id", currentUser.getDepartmentId());
            list.add(new UserHomeCardInfoVo("当前部门", String.valueOf(currentUser.getDepartmentName())));
            list.add(new UserHomeCardInfoVo("当前参与项目数", String.valueOf(projectStaffMapper.selectCount(projectStaffQueryWrapper))));
            list.add(new UserHomeCardInfoVo("用户上传文件数", String.valueOf(projectDocumentMapper.selectCount(projectDocumentQueryWrapper))));
            list.add(new UserHomeCardInfoVo("部门管理员数", String.valueOf(userMapper.selectCount(userQueryWrapper))));
        }
        return list;
    }
}

