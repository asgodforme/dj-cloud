package com.dj.cloud.facility.web;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.facility.entity.SystemInfo;
import com.dj.cloud.facility.service.SystemInfoService;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facility")
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    @PostMapping("/addSystemInfo")
    public Result<SystemInfo> addSystemInfo(@RequestBody SystemInfo systemInfo) {
        return systemInfoService.addSystemInfo(systemInfo);
    }

    @PostMapping("/deleteSystemInfo")
    public Result<SystemInfo> deleteSystemInfo(@RequestBody SystemInfo systemInfo) {
        return systemInfoService.deleteSystemInfo(systemInfo.getId());
    }

    @PostMapping("/updateSystemInfo")
    public Result<SystemInfo> updateSystemInfo(@RequestBody SystemInfo systemInfo) throws CoreException {
        return systemInfoService.updateSystemInfo(systemInfo);
    }

    @GetMapping("/querySystemInfo")
    public Result<PageResponse<List<SystemInfo>>> querySystemInfo(SystemInfo systemInfo) {
        return systemInfoService.querySystemInfo(systemInfo);
    }
}
