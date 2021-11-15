package com.dj.cloud.facility.service.impl;

import com.dj.cloud.facility.entity.SystemInfo;
import com.dj.cloud.facility.repository.SystemInfoRepository;
import com.dj.cloud.facility.service.SystemInfoService;
import com.dj.cloud.object.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    private SystemInfoRepository systemInfoRepository;

    @Override
    public Result<SystemInfo> addSystemInfo(SystemInfo systemInfo) {
        return Result.newResult(systemInfoRepository.save(systemInfo));
    }

    @Override
    public Result<SystemInfo> deleteSystemInfo(Integer id) {
        systemInfoRepository.deleteById(id);
        return Result.newResult(new SystemInfo(id));
    }

    @Override
    public Result<SystemInfo> updateSystemInfo(SystemInfo systemInfo) {
        return Result.newResult(systemInfoRepository.save(systemInfo));
    }

    @Override
    public Result<List<SystemInfo>> querySystemInfo(SystemInfo systemInfo) {
        return Result.newResult(systemInfoRepository.findAll(Example.of(systemInfo)));
    }
}
