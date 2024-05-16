package com.laityh.design.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseMapper;
import com.laityh.design.entity.Device;
import com.laityh.design.entity.DeviceType;
import com.laityh.design.entity.ProjectDocument;
import com.laityh.design.entity.vo.DeviceInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
    @Select(
            "select d.*, dt.device_kind " +
            "from device d, device_type dt " +
            "where d.device_type = dt.device_type_id " +
            "order by d.device_id"
    )
    IPage<DeviceInfoVo> selectDeviceInfoVoByPagination(Page<Device> page);

    @Select(
            "select dt.* from device_type dt order by dt.device_type_id"
    )
    IPage<DeviceType> selectDeviceTypeByPagination(Page<DeviceType> page);

    @Select(
            "select d.*, dt.device_kind " +
            "from device d, device_type dt " +
            "where d.device_type = dt.device_type_id and d.device_type = #{deviceTypeId} " +
            "order by d.device_id"
    )
    List<DeviceInfoVo> selectDeviceInfoVoByDeviceTypeId(int deviceTypeId);
}
