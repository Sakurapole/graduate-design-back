package com.laityh.design.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserManagementInfoPageVo {
    private Integer page;
    private Integer pageSize;
    private List<String> departmentName;
    private List<String> roleName;
}
