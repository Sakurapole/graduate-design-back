package com.laityh.design.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laityh.design.entity.vo.UserManagementInfoVo;
import com.laityh.design.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* <p>
    * Mapper接口
    * </p>
* @author laityh
* @since 2024-04-17
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.*, ur.role_name, d.department_name " +
            "from user u, user_role ur, department d " +
            "where u.id = #{userId} and u.role_id = ur.role_id and u.department_id = d.department_id")
    UserVo getUserInfo(int userId);

    @Select("select u.*, ur.role_name, d.department_name " +
            "from user u, user_role ur, department d " +
            "where u.role_id = 1 and u.role_id = ur.role_id and u.department_id = d.department_id")
    List<UserVo> getDepartmentAdminInfo();

    @Select("select u.*, ur.role_name, d.department_name " +
            "from user u, user_role ur, department d " +
            "${ew.customSqlSegment}" +
//            "where u.role_id = ur.role_id and u.department_id = d.department_id " +
            "order by u.id "
    )
    IPage<UserManagementInfoVo> selectUserManagementInfo(Page<UserManagementInfoVo> page, @Param("ew") QueryWrapper<User> wrapper);

    @Select("select distinct u.*, ur.role_name, d.department_name " +
            "from user u, user_role ur, department d, project p, design.project_staff ps " +
            "${ew.customSqlSegment}" +
//            "where u.role_id = ur.role_id and u.department_id = d.department_id " +
            "order by u.id "
    )
    IPage<UserManagementInfoVo> selectUserManagementInfoByProject(Page<UserManagementInfoVo> page, @Param("ew") QueryWrapper<User> wrapper);
}

