package com.laityh.design.controller;


import com.laityh.design.entity.vo.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

    import org.springframework.web.bind.annotation.RestController;
    import com.laityh.design.common.base.BaseController;
import com.laityh.design.service.IDepartmentService;
import com.laityh.design.entity.Department;

import java.util.List;
import java.util.Map;


/**
* @author laityh
* @since 2024-04-17
*/
@RestController
@RequestMapping("department")
public class DepartmentController extends BaseController<DepartmentVo, Department> {
    @Autowired
    private IDepartmentService departmentService;

    @PostMapping("/getAllDepartment")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @PostMapping("/updateDepartmentInfo")
    public String updateDepartmentInfo(@RequestBody DepartmentVo departmentVo) {
        return departmentService.updateDepartmentInfo(departmentVo);
    }

    @PostMapping("/deleteDepartmentById")
    public String deleteDepartmentById(@RequestBody Map<String, Object> map) {
        return departmentService.deleteDepartment(Integer.parseInt(map.get("departmentId").toString()));
    }

    @PostMapping("/insertDepartment")
    public String insertDepartment(@RequestBody Map<String, Object> map) {
        return departmentService.insertDepartment(map.get("departmentName").toString());
    }
}
