package com.laityh.design.common.interceptor;

import cn.dev33.satoken.stp.StpInterface;
import com.laityh.design.entity.User;
import com.laityh.design.mapper.UserMapper;
import com.laityh.design.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthGive implements StpInterface {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return List.of();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        Integer userId = Integer.parseInt((String) o);
        User user = userMapper.selectById(userId);
        List<String> roleList = new ArrayList<>();
        roleList.add(user.getRoleId().toString());
        return roleList;
    }
}
