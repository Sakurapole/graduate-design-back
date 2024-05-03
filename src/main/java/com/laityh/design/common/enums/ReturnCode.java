package com.laityh.design.common.enums;

import lombok.Getter;

@Getter
public enum ReturnCode {
    RETURN_CODE_0(0, "success"),
    RETURN_CODE_1(1, "error");
    private final int code;

    private final String msg;


    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
