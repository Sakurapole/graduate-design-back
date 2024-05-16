package com.laityh.design.controller;

import com.laityh.design.entity.DeviceType;
import com.laityh.design.entity.vo.*;
import com.laityh.design.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/getAllDevicePagination")
    public PageResultVo<DeviceInfoVo> getAllDevicePagination(@RequestBody PageVo pageVo) {
        return deviceService.getAllDevicePagination(pageVo);
    }

    @PostMapping("/updateDeviceInfoById")
    public String updateDeviceInfoById(@RequestBody DeviceInfoUpdateVo deviceInfoUpdateVo) {
        return deviceService.updateDeviceInfoById(deviceInfoUpdateVo);
    }

    @PostMapping("/deleteDeviceById")
    public String deleteDeviceById(@RequestBody Map<String, Object> map) {
        return deviceService.deleteDeviceById(Integer.parseInt(map.get("deviceId").toString()));
    }

    @PostMapping("/getAllDeviceType")
    public List<DeviceType> getAllDeviceType() {
        return deviceService.getAllDeviceType();
    }

    @PostMapping("/insertDevice")
    public String insertDevice(@RequestBody DeviceInsertVo deviceInsertVo) {
        return deviceService.insertDevice(deviceInsertVo);
    }

    @PostMapping("/getDeviceTypeByPagination")
    public PageResultVo<DeviceType> getDeviceTypeByPagination(@RequestBody PageVo pageVo) {
        return deviceService.getDeviceTypeByPagination(pageVo);
    }

    @PostMapping("/updateDeviceTypeById")
    public String updateDeviceTypeById(@RequestBody Map<String, Object> map) {
        return deviceService.updateDeviceTypeById(
                Integer.parseInt(map.get("deviceTypeId").toString()),
                map.get("deviceKind").toString()
        );
    }

    @PostMapping("/deleteDeviceType")
    public String deleteDeviceType(@RequestBody Map<String, Object> map) {
        return deviceService.deleteDeviceType(Integer.parseInt(map.get("deviceTypeId").toString()));
    }

    @PostMapping("/insertDeviceType")
    public String insertDeviceType(@RequestBody Map<String, Object> map) {
        return deviceService.insertDeviceType(map.get("deviceKind").toString());
    }

    @PostMapping("/getAllDeviceByDeviceTypeId")
    public List<DeviceInfoVo> getAllDeviceByDeviceTypeId(@RequestBody Map<String, Object> map) {
        return deviceService.getAllDeviceByDeviceTypeId(
                Integer.parseInt(map.get("deviceTypeId").toString())
        );
    }
}
