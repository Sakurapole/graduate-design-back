package com.laityh.design.common.handler;

import com.laityh.design.common.DO.ApiResultDO;
import com.laityh.design.common.enums.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResultDO<String> exception(Exception e) {
        log.error("全局异常捕获：ex={}", e.getMessage(), e);
        return ApiResultDO.error(ReturnCode.RETURN_CODE_1.getCode(), e.getMessage());
    }
}
