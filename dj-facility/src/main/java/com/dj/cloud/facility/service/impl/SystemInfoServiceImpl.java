package com.dj.cloud.facility.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.facility.entity.SystemInfo;
import com.dj.cloud.facility.repository.SystemInfoRepository;
import com.dj.cloud.facility.service.SystemInfoService;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Result<SystemInfo> updateSystemInfo(SystemInfo systemInfo) throws CoreException {
        SystemInfo localSystemInfo =  systemInfoRepository.findById(systemInfo.getId())
                .orElseThrow(() -> new CoreException("system info not exist", "系统信息不存在"));
        BeanUtils.copyProperties(localSystemInfo, systemInfo);
        return Result.newResult(systemInfoRepository.save(systemInfo));
    }

    @Override
    public Result<PageResponse<List<SystemInfo>>> querySystemInfo(SystemInfo systemInfo) {
        Pageable pageable = PageRequest.of(systemInfo.getCurrent() - 1, systemInfo.getPageSize());
        PageImpl<SystemInfo> page = (PageImpl<SystemInfo>) systemInfoRepository.findAll(Example.of(systemInfo), pageable);
        return Result.newResult(PageResponse.of(page.getTotalElements(), page.getContent()), "page");
    }
}
