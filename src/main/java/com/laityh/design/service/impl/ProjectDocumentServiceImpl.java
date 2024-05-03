package com.laityh.design.service.impl;

import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.entity.ProjectDocument;
import com.laityh.design.entity.vo.DailyDocumentNumVo;
import com.laityh.design.entity.vo.ProjectDocumentVo;
import com.laityh.design.mapper.ProjectDocumentMapper;
import com.laityh.design.service.IProjectDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDocumentServiceImpl
        extends BaseServiceImpl<ProjectDocumentVo, ProjectDocument>
        implements IProjectDocumentService {
    @Autowired
    ProjectDocumentMapper projectDocumentMapper;

    public List<DailyDocumentNumVo> getDailyDocumentNum() {
        List<Map<String, Object>> maps = projectDocumentMapper.executeNativeListSql("select DATE(create_time) as update_date, COUNT(*) AS upload_count\n" +
                "FROM project_document\n" +
                "WHERE create_time BETWEEN DATE_SUB(\"2024-04-22 20:56:20\", INTERVAL 6 DAY) AND \"2024-04-22 20:56:46\"\n" +
                "GROUP BY create_time\n" +
                "ORDER BY create_time;");
        Map<String, Integer> dateMap = new HashMap<>();
        maps.forEach(map -> {
            dateMap.put(map.get("update_date").toString(), Integer.parseInt(String.valueOf((Long) map.get("upload_count"))));
        });
        LocalDate today = LocalDate.now();
        today = today.minusDays(7);
        List<DailyDocumentNumVo> dailyDocumentNumVos = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String dateStr = date.toString();
            dailyDocumentNumVos.add(new DailyDocumentNumVo(dateStr, dateMap.getOrDefault(dateStr, 0)));
        }

        return dailyDocumentNumVos;
    }
}
