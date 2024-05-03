package com.laityh.design.entity.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.Data;

@Data
public class UserVo {
    private String userName;
    private Integer roleId;
    private String roleName;
    private Integer departmentId;
    private String departmentName;
    private String mail;
    private SaTokenInfo tokenInfo;
}
