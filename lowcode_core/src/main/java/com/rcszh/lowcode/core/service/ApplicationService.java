package com.rcszh.lowcode.core.service;
import com.rcszh.lowcode.core.entity.Application;
import com.rcszh.lowcode.core.mapper.ApplicationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    /**
     * 获取所有的应用
     */
    public Object findAllApplication() {
        return applicationMapper.selectList(null);
    }

    /**
     * 保存应用
     * @param application
     */
    public void saveOneApplication(Application application) {
        applicationMapper.insert(application);
    }
}
