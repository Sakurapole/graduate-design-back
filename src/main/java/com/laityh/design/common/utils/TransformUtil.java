package com.laityh.design.common.utils;

public class TransformUtil {
    public static Integer transformDepartmentByName(String departmentName) {
        return switch (departmentName) {
            case "超级管理员部门" -> 0;
            case "工程部门" -> 1;
            case "研发部门" -> 2;
            case "运维部门" -> 3;
            default -> -1;
        };
    }

    public static Integer transformRoleByName(String roleName) {
        return switch (roleName) {
            case "superAdmin" -> 0;
            case "departmentAdmin" -> 1;
            case "departmentStaff" -> 2;
            default -> -1;
        };
    }

    public static String transformProjectStatusToString(Integer projectStatus) {
        return switch (projectStatus) {
            case 0 -> "未开始";
            case 1 -> "一级进度";
            case 2 -> "二级进度";
            case 3 -> "三级进度";
            case 4 -> "已结项";
            default -> "未知";
        };
    }

    public static Integer transformProjectStatusFromString(String projectStatus) {
        return switch (projectStatus) {
            case "未开始" -> 0;
            case "一级进度" -> 1;
            case "二级进度" -> 2;
            case "三级进度" -> 3;
            case "已结项" -> 4;
            default -> -1;
        };
    }

    public static String transformDeviceStatusToString(Integer deviceStatus) {
        return switch (deviceStatus) {
            case 0 -> "正常";
            case 1 -> "需要维护";
            case 2 -> "需要更换";
            default -> "未知";
        };
    }

    public static Integer transformDeviceStatusFromString(String deviceStatusName) {
        return switch (deviceStatusName) {
            case "正常" -> 0;
            case "需要维护" -> 1;
            case "需要更换" -> 2;
            default -> -1;
        };
    }
}
