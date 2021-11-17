package com.dj.cloud.facility.service;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.facility.entity.SystemInfo;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;

import java.util.List;

public interface SystemInfoService {

    Result<SystemInfo> addSystemInfo(SystemInfo systemInfo);

    Result<SystemInfo> deleteSystemInfo(Integer id);

    Result<SystemInfo> updateSystemInfo(SystemInfo systemInfo) throws CoreException;

    Result<PageResponse<List<SystemInfo>>> querySystemInfo(SystemInfo systemInfo);
}
