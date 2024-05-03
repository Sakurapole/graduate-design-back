package com.laityh.design.controller;


import com.laityh.design.entity.vo.DepartmentVo;
import org.springframework.web.bind.annotation.RequestMapping;

    import org.springframework.web.bind.annotation.RestController;
    import com.laityh.design.common.base.BaseController;
import com.laityh.design.service.IDepartmentService;
import com.laityh.design.entity.Department;


/**
* @author laityh
* @since 2024-04-17
*/
@RestController
@RequestMapping("department")
public class DepartmentController extends BaseController<DepartmentVo, Department> {

}
