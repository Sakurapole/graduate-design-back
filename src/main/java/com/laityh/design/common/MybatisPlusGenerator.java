package com.laityh.design.common;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.laityh.design.common.base.BaseController;
import com.laityh.design.common.base.BaseDomain;
import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.common.base.IBaseService;

public class MybatisPlusGenerator {
    private static final String AUTHOR = "laityh";
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/design";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "20030303";

    private static final String PACKAGE_PARENT = "com.laityh";
    private static final String PACKAGE_MODULE_NAME = "design";
    private static final String PACKAGE_ENTITY = "entity";
    private static final String PACKAGE_MAPPER = "mapper";
    private static final String PACKAGE_SERVICE = "service";
    private static final String PACKAGE_SERVICE_IMPL = "service.impl";
    private static final String PACKAGE_CONTROLLER = "controller";
    private static final String TABLES = "user_role,department,user";
    private static final String PACKAGE_BASE = "com.laityh.design.common.base";

    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig.Builder().author(AUTHOR).outputDir(USER_DIR + "/src/main/java").build();
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig.Builder(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
    }

    /**
     * 包配置
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig.Builder()
                .parent(PACKAGE_PARENT)
                .moduleName(PACKAGE_MODULE_NAME)
                .entity(PACKAGE_ENTITY)
                .mapper(PACKAGE_MAPPER)
                .service(PACKAGE_SERVICE)
                .serviceImpl(PACKAGE_SERVICE_IMPL)
                .controller(PACKAGE_CONTROLLER)
                .xml("mapper.xml")
                .build();
    }

    private static TemplateConfig templateConfig() {
        return new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .xml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
    }

    private static StrategyConfig strategyConfig() {
        return new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
                .addInclude(TABLES.split(","))
                .addTablePrefix(packageConfig().getModuleName() + "_")
                .controllerBuilder()
                .enableFileOverride()
                .enableRestStyle()
                .superClass(BaseController.class)
                .enableRestStyle()
                .entityBuilder()
                .superClass(BaseDomain.class)
                .enableLombok()
                .enableFileOverride()
                .serviceBuilder()
                .enableFileOverride()
                .superServiceClass(IBaseService.class)
                .superServiceImplClass(BaseServiceImpl.class)
                .build();
    }

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator(dataSourceConfig());
        generator.strategy(strategyConfig());
        generator.global(globalConfig());
        generator.packageInfo(packageConfig());
        generator.template(templateConfig());
        generator.execute(new FreemarkerTemplateEngine());
    }
}
