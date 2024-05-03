package com.laityh.design.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class BaseServiceImpl<V, T> implements IBaseService<V, T> {
//    @Autowired
//    private BaseMapper<T> baseMapper;

    private final Class<V> entityVoClass;
    private final Class<T> entityClass;

    public BaseServiceImpl() {
        Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.entityVoClass = (Class<V>) types[0];
        this.entityClass = (Class<T>) types[1];
    }
}
