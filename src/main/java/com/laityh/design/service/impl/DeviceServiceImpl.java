package com.laityh.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laityh.design.common.base.BaseServiceImpl;
import com.laityh.design.common.utils.CopyUtil;
import com.laityh.design.entity.Device;
import com.laityh.design.entity.DeviceType;
import com.laityh.design.entity.vo.*;
import com.laityh.design.mapper.DeviceMapper;
import com.laityh.design.mapper.DeviceTypeMapper;
import com.laityh.design.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<DeviceVo, Device> implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public PageResultVo<DeviceInfoVo> getAllDevicePagination(PageVo pageVo) {
        Page<Device> page = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<DeviceInfoVo> deviceInfoVoIPage = deviceMapper.selectDeviceInfoVoByPagination(page);
        PageResultVo<DeviceInfoVo> resultVo = new PageResultVo<>();
        resultVo.setItems(deviceInfoVoIPage.getRecords());
        resultVo.setTotal((int) deviceInfoVoIPage.getTotal());
        return resultVo;
    }

    @Override
    public String updateDeviceInfoById(DeviceInfoUpdateVo deviceInfoUpdateVo) {
        Device device = deviceMapper.selectById(deviceInfoUpdateVo.getDeviceId());
        int newTotal = deviceInfoUpdateVo.getCurrentNumber() - device.getCurrentNumber() + device.getTotalNumber();
        UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("device_id", deviceInfoUpdateVo.getDeviceId());
        updateWrapper.set("device_name", deviceInfoUpdateVo.getDeviceName());
        updateWrapper.set("current_number", deviceInfoUpdateVo.getCurrentNumber());
        updateWrapper.set("total_number", newTotal);
        updateWrapper.set("update_time", LocalDateTime.now());
        return deviceMapper.update(null, updateWrapper) > 0 ? "success" : "failed";
    }

    @Override
    public String deleteDeviceById(int deviceId) {
        return deviceMapper.deleteById(deviceId) > 0 ? "success" : "failed";
    }

    @Override
    public List<DeviceType> getAllDeviceType() {
        QueryWrapper<DeviceType> wrapper = new QueryWrapper<>();
        return deviceTypeMapper.selectList(wrapper);
    }

    @Override
    public String insertDevice(DeviceInsertVo deviceInsertVo) {
        Device copy = CopyUtil.copy(deviceInsertVo, Device.class);
        copy.setCreateTime(LocalDateTime.now());
        copy.setUpdateTime(LocalDateTime.now());
        copy.setCurrentNumber(copy.getTotalNumber());
        return deviceMapper.insert(copy) > 0 ? "success" : "failed";
    }

    @Override
    public PageResultVo<DeviceType> getDeviceTypeByPagination(PageVo pageVo) {
        Page<DeviceType> page = new Page<>(pageVo.getPage(), pageVo.getPageSize());
        IPage<DeviceType> deviceTypeIPage = deviceMapper.selectDeviceTypeByPagination(page);
        PageResultVo<DeviceType> resultVo = new PageResultVo<>();
        resultVo.setItems(deviceTypeIPage.getRecords());
        resultVo.setTotal((int) deviceTypeIPage.getTotal());
        return resultVo;
    }

    @Override
    public String updateDeviceTypeById(int deviceTypeId, String deviceKind) {
        UpdateWrapper<DeviceType> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("device_type_id", deviceTypeId);
        updateWrapper.set("device_kind", deviceKind);
        return deviceTypeMapper.update(null, updateWrapper) > 0 ? "success" : "failed";
    }

    @Override
    public String deleteDeviceType(int deviceTypeId) {
        return deviceTypeMapper.deleteById(deviceTypeId) > 0 ? "success" : "failed";
    }

    @Override
    public String insertDeviceType(String deviceKind) {
        DeviceType deviceType = new DeviceType();
        deviceType.setDeviceKind(deviceKind);
        deviceType.setCreateTime(LocalDateTime.now());
        deviceType.setUpdateTime(LocalDateTime.now());
        return deviceTypeMapper.insert(deviceType) > 0 ? "success" : "failed";
    }

    @Override
    public List<DeviceInfoVo> getAllDeviceByDeviceTypeId(int deviceTypeId) {
        return deviceMapper.selectDeviceInfoVoByDeviceTypeId(deviceTypeId);
    }


}
