package com.laityh.design.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.exceptions.CustomException;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.common.utils.TransformUtil;
import com.laityh.design.entity.*;
import com.laityh.design.entity.vo.*;
import com.laityh.design.mapper.*;
import com.laityh.design.service.IUserService;
import com.laityh.design.common.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public PageResultVo<UserManagementInfoVo> getUserInfoByPagination(UserManagementInfoPageVo pageVo) {
        int userId = StpUtil.getLoginIdAsInt();
        User currentUser = userMapper.selectById(userId);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("u.role_id = ur.role_id and u.department_id = d.department_id");
        if (currentUser.getRoleId() == 0) {
            if (pageVo.getDepartmentName() != null && !pageVo.getDepartmentName().isEmpty()) {
                wrapper.in("d.department_name", pageVo.getDepartmentName());
            }
            if (pageVo.getRoleName() != null && !pageVo.getRoleName().isEmpty()) {
                wrapper.in("ur.role_name", pageVo.getRoleName());
            }
        } else {
            wrapper.eq("u.department_id", currentUser.getDepartmentId());
            wrapper.eq("u.role_id", 2);
        }
        Page<UserManagementInfoVo> page = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<UserManagementInfoVo> userManagementInfoVoIPage = userMapper.selectUserManagementInfo(page, wrapper);
        PageResultVo<UserManagementInfoVo> resultVo = new PageResultVo<>();
        resultVo.setTotal((int) userManagementInfoVoIPage.getTotal());
        resultVo.setItems(userManagementInfoVoIPage.getRecords());
        return resultVo;
    }

    @Override
    public String updateUserInfoByAdmin(UserManagementInfoVo userManagementInfoVo) {
        User user = CopyUtil.copy(userManagementInfoVo, User.class);
        user.setDepartmentId(TransformUtil.transformDepartmentByName(userManagementInfoVo.getDepartmentName()));
        user.setRoleId(TransformUtil.transformRoleByName(userManagementInfoVo.getRoleName()));
        user.setUpdateTime(LocalDateTime.now());
        int res = userMapper.updateById(user);
        return res > 0 ? "更新成功" : "更新失败";
    }

    @Override
    public String deleteUserInfoByAdmin(Integer userId) {
        return userMapper.deleteById(userId) > 0 ? "删除成功" : "删除失败";
    }

    @Override
    public User insertUserInfoByAdmin(UserAddRequestVo userAddRequestVo) {
        User user = new User();
        user.setUserName(userAddRequestVo.getUserName());
        user.setComment("");
        user.setMail(userAddRequestVo.getMail());
        user.setPassword(userAddRequestVo.getPassword());
        user.setRoleId(TransformUtil.transformRoleByName(userAddRequestVo.getUserRole()));
        user.setDepartmentId(userAddRequestVo.getDepartmentId());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        if (userMapper.insert(user) == 0) {
            user.setId(-2);
        } else {
            user.setId(-1);
        }
        return user;
    }

    @Override
    public UserVo getUserInfoById(int id) {
        UserVo userInfo = userMapper.getUserInfo(id);
        userInfo.setTokenInfo(null);
        return userInfo;
    }

    @Override
    public List<UserVo> getAllDepartmentAdmin() {
        return userMapper.getDepartmentAdminInfo();
    }
}

