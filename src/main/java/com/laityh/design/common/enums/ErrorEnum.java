package com.laityh.design.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorEnum {
    INVALID_USER_NAME(400, "无效用户名"),
    INVALID_USER_PASSWORD(400, "无效密码");

    private final Integer value;
    private final String label;
}
