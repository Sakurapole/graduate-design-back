package com.laityh.design.common.config;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }

//    @Bean
//    public SaServletFilter getSaServletFilter() {
//        return new SaServletFilter()
//                .addInclude("/**")
//                .addExclude("/user/login")
//                .setAuth(r -> {
////                    SaManager.getLog().debug("----- 请求path={}  提交token={}", SaHolder.getRequest().getRequestPath(), StpUtil.getTokenValue());
//                    StpUtil.checkLogin();
//                })
//                .setBeforeAuth(obj -> {
//                    SaHolder.getResponse()
//                            .setHeader("Access-Control-Allow-Origin", "*")
//                            // 允许所有请求方式
//                            .setHeader("Access-Control-Allow-Methods", "*")
//                            // 允许的header参数
//                            .setHeader("Access-Control-Allow-Headers", "*")
//                            // 有效时间
//                            .setHeader("Access-Control-Max-Age", "3600");
//                });
//    }
}
