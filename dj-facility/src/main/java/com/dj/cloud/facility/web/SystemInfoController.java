package com.dj.cloud.facility.web;

import com.dj.cloud.facility.entity.SystemInfo;
import com.dj.cloud.facility.service.SystemInfoService;
import com.dj.cloud.object.vo.Result;
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

    @PutMapping("/updateSystemInfo")
    public Result<SystemInfo> updateSystemInfo(SystemInfo systemInfo) {
        return systemInfoService.updateSystemInfo(systemInfo);
    }

    @GetMapping("/querySystemInfo")
    public Result<List<SystemInfo>> querySystemInfo(SystemInfo systemInfo) {
        return systemInfoService.querySystemInfo(systemInfo);
    }
}
