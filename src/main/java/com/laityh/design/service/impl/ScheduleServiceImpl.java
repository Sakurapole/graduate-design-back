package com.laityh.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.entity.Schedule;
import com.laityh.design.entity.vo.ScheduleVo;
import com.laityh.design.mapper.ProjectMapper;
import com.laityh.design.mapper.ScheduleMapper;
import com.laityh.design.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScheduleServiceImpl extends BaseServiceImpl<ScheduleVo, Schedule> implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public String updateProjectScheduleInfo(Map<String, Object> map) {
        UpdateWrapper<Schedule> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", Integer.parseInt(map.get("projectId").toString()));
        String first = map.get("firstScheduleDescription").toString();
        String second = map.get("secondScheduleDescription").toString();
        String third = map.get("thirdScheduleDescription").toString();
        updateWrapper.set("first_schedule_description", first);
        updateWrapper.set("second_schedule_description", second);
        updateWrapper.set("third_schedule_description", third);
        return scheduleMapper.update(null, updateWrapper) > 0 ? "success" : "failed";
    }
}
