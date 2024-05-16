package com.laityh.design.service;

import com.laityh.design.entity.Department;
import com.laityh.design.common.base.IBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laityh.design.entity.vo.DepartmentVo;

import java.util.List;

/**
* <p>
    *  服务类
    * </p>
* @author laityh
* @since 2024-04-17
*/
public interface IDepartmentService extends IBaseService<DepartmentVo, Department> {

    List<Department> getAllDepartment();

    String updateDepartmentInfo(DepartmentVo departmentVo);

    String deleteDepartment(Integer departmentId);

    String insertDepartment(String departmentName);
}

