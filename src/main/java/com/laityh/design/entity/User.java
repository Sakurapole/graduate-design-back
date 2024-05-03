package com.laityh.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

        import java.time.LocalDateTime;

/**
* @author laityh
* @since 2024-04-17
*/
@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "role_id")
    private Integer roleId;

    @TableField(value = "department_id")
    private Integer departmentId;

    @TableField(value = "password")
    private String password;

    @TableField(value = "comment")
    private String comment;

    @TableField(value = "mail")
    private String mail;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
