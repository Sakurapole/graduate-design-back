package com.laityh.design;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableWebMvc
@SpringBootApplication
@MapperScan("com.laityh.design.mapper")
public class DesignApplication {
    public static void main(String[] args) {
        // 配置Log4j2异步
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication app = new SpringApplication(DesignApplication.class);
        ConfigurableApplicationContext applicationContext = app.run(args);
        Environment env = applicationContext.getEnvironment();
        System.out.println("Sa-Token配置：" + SaManager.getConfig());
    }
}
