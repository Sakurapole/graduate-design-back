package com.laityh.design.entity.vo;

import lombok.Data;

@Data
public class UserAddRequestVo {
    private Integer departmentId;
    private String mail;
    private String password;
    private String userName;
    private String userRole;
}
