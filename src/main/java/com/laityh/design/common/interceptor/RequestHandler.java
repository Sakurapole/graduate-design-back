package com.laityh.design.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.laityh.design.common.utils.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author LaityH
 */
@Slf4j
public class RequestHandler implements HandlerInterceptor {
    // 当前线程的计时器
    private static final ThreadLocal<StopWatch> TIME = new ThreadLocal<>();
    // 当前线程需要输出的日志
    private static final ThreadLocal<StringBuilder> LOG_INFO = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // API请求开始时计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 放入本线程
        TIME.set(stopWatch);
        // 请求的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 请求的headers
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>(16);
        if (headerNames != null) {
            while(headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headerMap.put(headerName, request.getHeader(headerName));
            }
        }
        // 请求ID 唯一
        String requestId = String.valueOf(UUID.randomUUID()).replaceAll("-", "");
        MDC.put("requestId", requestId);
        StringBuilder log_info = new StringBuilder();
        log_info.append("\n【request_id】：").append(requestId);
        log_info.append("\n【请求 URL】：").append(request.getRequestURL());
        log_info.append("\n【请求 IP】：").append(IpUtil.getIp(request));
        log_info.append("\n【请求Header：】").append(JSON.toJSONString(headerMap));
        log_info.append("\n【请求参数】：").append(JSON.toJSONString(parameterMap));
        LOG_INFO.set(log_info);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        StopWatch stopWatch = TIME.get();
        stopWatch.stop();
        long time = stopWatch.getTotalTimeMillis();
        TIME.remove();

        StringBuilder log_info = LOG_INFO.get();
        LOG_INFO.remove();
        log_info.append("\n【接口耗时：】").append(time).append("ms");
        log.info(log_info.toString());
        MDC.clear();
    }
}
