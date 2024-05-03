package com.laityh.design.service;

import com.laityh.design.entity.UserRole;
import com.laityh.design.common.base.IBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laityh.design.entity.vo.UserRoleVo;
import com.laityh.design.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author laityh
* @since 2024-04-17
*/
public interface IUserRoleService extends IBaseService<UserRoleVo, UserRole> {

}

