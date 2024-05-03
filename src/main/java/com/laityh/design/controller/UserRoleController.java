package com.laityh.design.controller;


import com.laityh.design.mapper.UserRoleMapper;
import org.springframework.web.bind.annotation.RequestMapping;

    import org.springframework.web.bind.annotation.RestController;
    import com.laityh.design.common.base.BaseController;
import com.laityh.design.service.IUserRoleService;
import com.laityh.design.entity.UserRole;


/**
* <p>
    *  前端控制器
    * </p>
* @author laityh
* @since 2024-04-17
*/
@RestController
@RequestMapping("user/role")
public class UserRoleController extends BaseController<UserRoleMapper, UserRole> {

}
