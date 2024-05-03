package com.laityh.design.service.impl;

import com.laityh.design.entity.UserRole;
import com.laityh.design.entity.vo.UserRoleVo;
import com.laityh.design.mapper.UserRoleMapper;
import com.laityh.design.service.IUserRoleService;
import com.laityh.design.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author laityh
* @since 2024-04-17
*/
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleVo, UserRole> implements IUserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
}

