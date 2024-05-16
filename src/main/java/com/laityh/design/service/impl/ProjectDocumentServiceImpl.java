package com.laityh.design.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.common.utils.TransformUtil;
import com.laityh.design.entity.Project;
import com.laityh.design.entity.ProjectDocument;
import com.laityh.design.entity.vo.*;
import com.laityh.design.mapper.ProjectDocumentMapper;
import com.laityh.design.mapper.ProjectMapper;
import com.laityh.design.mapper.UserMapper;
import com.laityh.design.service.IProjectDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectDocumentServiceImpl
        extends BaseServiceImpl<ProjectDocumentVo, ProjectDocument>
        implements IProjectDocumentService {
    @Autowired
    ProjectDocumentMapper projectDocumentMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ResourceLoader resourceLoader;

    private String uploadPath = "/home/laityh/Files/";

    public List<DailyDocumentNumVo> getDailyDocumentNum() {
        List<Map<String, Object>> maps = projectDocumentMapper.executeNativeListSql("select DATE(create_time) as update_date, COUNT(*) AS upload_count " +
                "FROM project_document " +
                "WHERE create_time BETWEEN DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 6 DAY) AND CURRENT_TIMESTAMP " +
                "GROUP BY create_time " +
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

    @Override
    public PageResultVo<ProjectDocumentDetailVo> getProjectDocumentDetailByPagination(ProjectDocumentDetailRequestVo pageVo) {
        QueryWrapper<ProjectDocument> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("pd.project_id = p.project_id and pd.user_id = u.id and pd.project_id = " + pageVo.getProjectId());
        IPage<ProjectDocumentDetailVo> page = projectDocumentMapper.selectProjectDocumentDetailVo(new Page<>(pageVo.getPage(), pageVo.getPageSize()), queryWrapper);
        PageResultVo<ProjectDocumentDetailVo> resultVo = new PageResultVo<>();
        resultVo.setItems(page.getRecords().stream().peek(data -> {
            data.setDocumentProjectSchedule(TransformUtil.transformProjectStatusToString(data.getDocumentNumberSchedule()));
        }).collect(Collectors.toList()));
        resultVo.setTotal((int) page.getTotal());
        return resultVo;
    }

    @Override
    public String uploadDocumentFile(ProjectDocumentUploadVo projectDocumentUploadVo) throws IOException {
        for (MultipartFile file : projectDocumentUploadVo.getFile()) {
            System.out.println(file.getOriginalFilename());
            file.transferTo(new File(uploadPath + file.getOriginalFilename()));
            Project project = projectMapper.selectById(projectDocumentUploadVo.getProjectId());
            ProjectDocument document = new ProjectDocument();
            document.setDocumentName(file.getOriginalFilename());
            document.setDocumentPath(uploadPath + file.getOriginalFilename());
            document.setDocumentNumberSchedule(project.getProjectStatus());
            document.setUserId(StpUtil.getLoginIdAsInt());
            document.setProjectId(projectDocumentUploadVo.getProjectId());
            document.setCreateTime(LocalDateTime.now());
            document.setUpdateTime(LocalDateTime.now());
            projectDocumentMapper.insert(document);
        }
        return "success";
    }

    @Override
    public String deleteDocumentFile(int documentId) {
        return projectDocumentMapper.deleteById(documentId) > 0 ? "success" : "failed";
    }

    @Override
    public ResponseEntity<Resource> downloadDocumentFile(int documentId) throws IOException {
        ProjectDocument pd = projectDocumentMapper.selectById(documentId);
        Path path = Paths.get(pd.getDocumentPath()).normalize();
        System.out.println(path.toUri().getPath());
        Resource resource = new UrlResource(path.toUri());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(pd.getDocumentName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        headers.setContentLength(resource.contentLength());
        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
