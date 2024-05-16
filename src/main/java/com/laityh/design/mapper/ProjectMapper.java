package com.laityh.design.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseMapper;
import com.laityh.design.entity.Project;
import com.laityh.design.entity.User;
import com.laityh.design.entity.vo.ProjectDetailVo;
import com.laityh.design.entity.vo.ProjectManagementVo;
import com.laityh.design.entity.vo.ProjectSelectVo;
import com.laityh.design.entity.vo.UserManagementInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    @Select(
            "select p.*, u.user_name as project_admin_name " +
            "from project p, user u " +
            "${ew.customSqlSegment}" +
            "order by p.project_id "
    )
    IPage<ProjectManagementVo> selectProjectManagementInfo(Page<ProjectManagementVo> page, @Param("ew") QueryWrapper<Project> wrapper);

    @Select(
            "select p.project_id, p.project_name " +
            "from project p " +
            "${ew.customSqlSegment}" +
            "order by p.project_id"
    )
    List<ProjectSelectVo> selectProjectSelectInfo(@Param("ew") QueryWrapper<Project> wrapper);

    @Select(
            "select p.project_id, p.project_name, p.project_admin_id, p.project_status, " +
            "p.project_description, p.create_time as project_create_time, s.schedule_id, s.first_schedule_description, " +
            "s.second_schedule_description, s.third_schedule_description, s.create_time as schedule_create_time, " +
            "s.update_time as schedule_update_time, u.user_name as project_admin_name " +
            "from project p , `schedule` s, user u " +
            "where p.project_id = #{projectId} and p.project_id = s.project_id and p.project_admin_id = u.id"
    )
    ProjectDetailVo selectProjectDetailInfo(int projectId);
}
