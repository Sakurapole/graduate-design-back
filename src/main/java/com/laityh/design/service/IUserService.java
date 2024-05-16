package com.laityh.design.service;

import com.laityh.design.entity.User;
import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.vo.*;

import java.util.List;

/**
* <p>
    *  服务类
    * </p>
* @author laityh
* @since 2024-04-17
*/
public interface IUserService extends IBaseService<UserVo, User> {
    public List<UserVo> getAllUser();

    public User validateLogin(LoginUserVo loginUserVo) throws Exception;

    public UserVo getUserInfo();

    public List<UserHomeCardInfoVo> getUserHomeCardInfo();

//    public PageResultVo<UserManagementInfoVo> getUserInfoByPagination(PageVo pageVo);

    public PageResultVo<UserManagementInfoVo> getUserInfoByPagination(UserManagementInfoPageVo pageVo);

    String updateUserInfoByAdmin(UserManagementInfoVo userManagementInfoVo);

    String deleteUserInfoByAdmin(Integer userId);

    User insertUserInfoByAdmin(UserAddRequestVo userAddRequestVo);

    UserVo getUserInfoById(int id);

    List<UserVo> getAllDepartmentAdmin();
}

