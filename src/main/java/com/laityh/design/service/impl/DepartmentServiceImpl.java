package com.laityh.design.service.impl;

import com.laityh.design.common.base.BaseMapper;
import com.laityh.design.entity.Department;
import com.laityh.design.entity.vo.DepartmentVo;
import com.laityh.design.mapper.DepartmentMapper;
import com.laityh.design.service.IDepartmentService;
import com.laityh.design.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* <p>
    *  服务实现类
    * </p>
* @author laityh
* @since 2024-04-17
*/
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentVo, Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
}

