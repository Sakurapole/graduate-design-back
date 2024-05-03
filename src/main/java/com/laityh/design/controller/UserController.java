package com.laityh.design.controller;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.entity.vo.LoginUserVo;
import com.laityh.design.entity.vo.UserHomeCardInfoVo;
import com.laityh.design.entity.vo.UserVo;
import com.laityh.design.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.laityh.design.common.base.BaseController;
import com.laityh.design.service.IUserService;
import com.laityh.design.entity.User;

import java.util.List;


/**
* <p>
    *  前端控制器
    * </p>
* @author laityh
* @since 2024-04-17
*/
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserVo, User> {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public UserVo login(@RequestBody LoginUserVo loginUserVo) throws Exception {
        User user = userService.validateLogin(loginUserVo);
        StpUtil.login(user.getId());
        UserVo userVo = CopyUtil.copy(user, UserVo.class);
        userVo.setTokenInfo(StpUtil.getTokenInfo());
        return userVo;
    }

    @PostMapping("/logout")
    public String logout() {
        StpUtil.logout();
        return "用户已注销";
    }

    @PostMapping("/getUserInfo")
    public UserVo getUserInfo() {
        return userService.getUserInfo();
    }

    @PostMapping("/getUserHomeCardInfo")
    public List<UserHomeCardInfoVo> getUserHomeCardINfo() {
        return userService.getUserHomeCardInfo();
    }

    @PostMapping("/getAllUser")
    public List<UserVo> getAllUser() {
        return userService.getAllUser();
    }
}
