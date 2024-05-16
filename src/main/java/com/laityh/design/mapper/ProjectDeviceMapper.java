package com.laityh.design.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseMapper;
import com.laityh.design.entity.ProjectDevice;
import com.laityh.design.entity.vo.ProjectDeviceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProjectDeviceMapper extends BaseMapper<ProjectDevice> {
    @Select(
            "select pd.*, p.project_name, d.device_name, dt.device_kind, dt.device_type_id " +
            "from device d, device_type dt, project_device pd, project p " +
            "where d.device_type = dt.device_type_id and pd.device_id = d.device_id and pd.project_id = p.project_id and p.project_id = #{projectId}"
    )
    IPage<ProjectDeviceVo> selectProjectDeviceVoByPagination(Page<ProjectDevice> page, int projectId);
}
