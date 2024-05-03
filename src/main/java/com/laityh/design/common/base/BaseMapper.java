package com.laityh.design.common.base;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    @Update(value = "${sql}")
    int executeNativeUpdateSql(String sql);

//    @Select(value = "${sql}")
    Map<String, Object> executeNativeOneSql(String sql);

    @Select(value = "${sql}")
    List<Map<String, Object>> executeNativeListSql(String sql);

    @Select(value = "${sql}")
    T executeNativeFindOneSql(String sql);

    @Select(value = "${sql}")
    List<T> executeNativeFindList(String sql);

    @Select(value = "${sql}")
    int executeNativeCountSql(String sql);
}
