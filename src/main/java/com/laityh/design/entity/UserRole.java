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
@TableName("user_role")
public class UserRole {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
