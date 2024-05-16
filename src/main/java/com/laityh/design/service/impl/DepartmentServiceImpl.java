package com.laityh.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.entity.Department;
import com.laityh.design.entity.User;
import com.laityh.design.entity.vo.DepartmentVo;
import com.laityh.design.mapper.DepartmentMapper;
import com.laityh.design.mapper.UserMapper;
import com.laityh.design.service.IDepartmentService;
import com.laityh.design.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Department> getAllDepartment() {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        return departmentMapper.selectList(wrapper);
    }

    @Override
    public String updateDepartmentInfo(DepartmentVo departmentVo) {
        UpdateWrapper<Department> wrapper = new UpdateWrapper<>();
        wrapper.eq("department_id", departmentVo.getDepartmentId());
        wrapper.set("department_name", departmentVo.getDepartmentName());
        wrapper.set("update_time", LocalDateTime.now());
        return departmentMapper.update(null, wrapper) > 0 ? "更新成功" : "更新失败";
    }

    @Override
    public String deleteDepartment(Integer departmentId) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("department_id", departmentId);
        wrapper.set("department_id", 9999);
        userMapper.update(null, wrapper);
        return departmentMapper.deleteById(departmentId) > 0 ? "删除成功" : "删除失败";
    }

    @Override
    public String insertDepartment(String departmentName) {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setCreateTime(LocalDateTime.now());
        department.setUpdateTime(LocalDateTime.now());
        return departmentMapper.insert(department) > 0 ? "创建成功" : "创建失败";
    }
}

