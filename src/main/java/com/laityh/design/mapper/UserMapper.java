package com.laityh.design.mapper;

import com.laityh.design.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laityh.design.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}

