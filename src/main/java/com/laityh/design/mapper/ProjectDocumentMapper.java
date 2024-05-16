package com.laityh.design.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseMapper;
import com.laityh.design.entity.ProjectDocument;
import com.laityh.design.entity.vo.ProjectDocumentDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProjectDocumentMapper extends BaseMapper<ProjectDocument> {
    @Select(
            "select p.project_name, pd.document_number_schedule, pd.document_id, pd.document_name, pd.project_id, pd.user_id, u.user_name as upload_user_name, pd.create_time " +
            "from project_document pd, project p, user u " +
            "${ew.customSqlSegment}" +
            "order by pd.document_id "
    )
    public IPage<ProjectDocumentDetailVo> selectProjectDocumentDetailVo(Page<ProjectDocument> page, @Param("ew") Wrapper<ProjectDocument> queryWrapper);
}
