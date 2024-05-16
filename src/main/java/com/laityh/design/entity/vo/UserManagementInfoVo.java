package com.laityh.design.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserManagementInfoVo {
    private Integer id;
    private String userName;
    private Integer roleId;
    private String roleName;
    private Integer departmentId;
    private String departmentName;
    private String password;
    private String comment;
    private String mail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime updateTime;
}
