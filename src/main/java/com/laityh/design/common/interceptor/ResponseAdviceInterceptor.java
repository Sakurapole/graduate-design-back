package com.laityh.design.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laityh.design.common.DO.ApiResultDO;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.laityh.design")
public class ResponseAdviceInterceptor implements ResponseBodyAdvice {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return objectMapper.writeValueAsString(ApiResultDO.success(body));
        }
        if (body instanceof ApiResultDO) {
            return body;
        }
        return ApiResultDO.success(body);
    }
}
