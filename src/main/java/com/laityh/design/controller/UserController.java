package com.laityh.design.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.entity.vo.*;
import com.laityh.design.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.laityh.design.common.base.BaseController;
import com.laityh.design.service.IUserService;
import com.laityh.design.entity.User;

import java.util.List;
import java.util.Map;


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
    private IUserService userService;

    @PostMapping("/login")
    public UserVo login(@RequestBody LoginUserVo loginUserVo) throws Exception {
        User user = userService.validateLogin(loginUserVo);
        StpUtil.login(user.getId());
        UserVo userVo = CopyUtil.copy(user, UserVo.class);
        StpUtil.getRoleList();
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

    @SaCheckRole(value = {"0", "1"}, mode = SaMode.OR)
    @PostMapping("/getAllDepartmentAdmin")
    public List<UserVo> getAllDepartmentAdmin() {
        return userService.getAllDepartmentAdmin();
    }

    @PostMapping("/getUserInfoById")
    public UserVo getUserInfoById(@RequestBody Map<String, Object> map) {
        return userService.getUserInfoById(Integer.parseInt(map.get("id").toString()));
    }

    @PostMapping("/getUserHomeCardInfo")
    public List<UserHomeCardInfoVo> getUserHomeCardINfo() {
        return userService.getUserHomeCardInfo();
    }

    @SaCheckRole(value = {"0", "1"}, mode = SaMode.OR)
    @PostMapping("/getAllUser")
    public List<UserVo> getAllUser() {
        return userService.getAllUser();
    }

    @SaCheckRole(value = {"0", "1"}, mode = SaMode.OR)
    @PostMapping("/getUserInfoByPagination")
    public PageResultVo<UserManagementInfoVo> getUserInfoByPagination(@RequestBody UserManagementInfoPageVo pageVo) {
        return userService.getUserInfoByPagination(pageVo);
    }

    @SaCheckRole(value = {"0", "1"}, mode = SaMode.OR)
    @PostMapping("/insertUserInfoByAdmin")
    public User insertUserInfoByAdmin(@RequestBody UserAddRequestVo userAddRequestVo) {
        return userService.insertUserInfoByAdmin(userAddRequestVo);
    }

    @SaCheckRole(value = {"0", "1"}, mode = SaMode.OR)
    @PostMapping("/updateUserInfoByAdmin")
    public String updateUserInfoByAdmin(@RequestBody UserManagementInfoVo userManagementInfoVo) {
        return userService.updateUserInfoByAdmin(userManagementInfoVo);
    }

    @SaCheckRole(value = {"0", "1"}, mode = SaMode.OR)
    @PostMapping("/deleteUserInfoByAdmin")
    public String deleteUserInfoByAdmin(@RequestBody Map<String, Object> map) {
        return userService.deleteUserInfoByAdmin(Integer.parseInt(map.get("userId").toString()));
    }
}
