package com.laityh.design.service;

import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.Schedule;
import com.laityh.design.entity.vo.ScheduleVo;

import java.util.Map;

public interface IScheduleService extends IBaseService<ScheduleVo, Schedule> {
    String updateProjectScheduleInfo(Map<String, Object> map);
}
