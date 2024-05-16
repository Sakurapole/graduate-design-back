package com.laityh.design.service;

import com.laityh.design.common.base.IBaseService;
import com.laityh.design.entity.Device;
import com.laityh.design.entity.DeviceType;
import com.laityh.design.entity.vo.*;

import java.util.List;

public interface IDeviceService extends IBaseService<DeviceVo, Device> {
    PageResultVo<DeviceInfoVo> getAllDevicePagination(PageVo pageVo);

    String updateDeviceInfoById(DeviceInfoUpdateVo deviceInfoUpdateVo);

    String deleteDeviceById(int deviceId);

    List<DeviceType> getAllDeviceType();

    String insertDevice(DeviceInsertVo deviceInsertVo);

    PageResultVo<DeviceType> getDeviceTypeByPagination(PageVo pageVo);

    String updateDeviceTypeById(int deviceTypeId, String deviceKind);

    String deleteDeviceType(int deviceTypeId);

    String insertDeviceType(String deviceKind);

    List<DeviceInfoVo> getAllDeviceByDeviceTypeId(int deviceTypeId);
}
