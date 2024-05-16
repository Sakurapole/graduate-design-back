package com.laityh.design.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResultVo<T> {
    private Integer total;
    private List<T> items;
}
