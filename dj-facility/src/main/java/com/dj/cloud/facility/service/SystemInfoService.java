package com.dj.cloud.facility.service;

import com.dj.cloud.facility.entity.SystemInfo;
import com.dj.cloud.object.vo.Result;

import java.util.List;

public interface SystemInfoService {

    Result<SystemInfo> addSystemInfo(SystemInfo systemInfo);

    Result<SystemInfo> deleteSystemInfo(Integer id);

    Result<SystemInfo> updateSystemInfo(SystemInfo systemInfo);

    Result<List<SystemInfo>> querySystemInfo(SystemInfo systemInfo);
}
