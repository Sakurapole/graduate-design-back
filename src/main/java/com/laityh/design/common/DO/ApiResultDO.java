package com.laityh.design.common.DO;

import com.laityh.design.common.enums.ReturnCode;
import lombok.Data;

@Data
public class ApiResultDO<T> {
    // 状态码
    private int code;
    // 响应信息
    private String msg;
    // 接口响应数据
    private T data;
    // 请求时间戳
    private long timestamp;

    public ApiResultDO() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResultDO<T> success(T data) {
        ApiResultDO<T> apiResultDO = new ApiResultDO<>();
        apiResultDO.setCode(ReturnCode.RETURN_CODE_0.getCode());
        apiResultDO.setMsg(ReturnCode.RETURN_CODE_0.getMsg());
        apiResultDO.setData(data);
        return apiResultDO;
    }

    public static <T> ApiResultDO<T> error(int code, String msg) {
        ApiResultDO<T> apiResultDO = new ApiResultDO<>();
        apiResultDO.setCode(code);
        apiResultDO.setMsg(msg);
        return apiResultDO;
    }
}
