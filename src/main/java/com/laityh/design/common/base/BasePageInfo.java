package com.laityh.design.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laityh.design.common.utils.CopyUtil;
import lombok.Data;

import java.util.List;

/**
 * 基础分页对象
 * @param <T>
 */
@Data
public class BasePageInfo<T> {
    private int page; //当前页码
    private int pageSize; //页面大小

    private List<T> data; //分页结果
    private int records; //总记录数
    private int totalPageNum; //总页数

    public static <T> BasePageInfo<T> of(IPage page, Class<T> entitiyVoClass) {
        BasePageInfo<T> pageInfo = new BasePageInfo<>();
        int records = (int) page.getTotal();
        int pageSize = (int) page.getSize();
        int total = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;

        pageInfo.setPageSize((int) page.getSize());
        pageInfo.setPage((int) page.getCurrent());
        pageInfo.setData(CopyUtil.copyList(page.getRecords(), entitiyVoClass));
        pageInfo.setRecords(records);
        pageInfo.setTotalPageNum(total);

        return pageInfo;
    }
}
