package com.rcszh.lowcode.salary.service;

import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.account.entity.FakeUser;
import com.rcszh.lowcode.account.service.FakeUserService;
import com.rcszh.lowcode.salary.dto.SalaryQueryParamDto;
import com.rcszh.lowcode.salary.entity.Salary;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.mapper.SalaryMapper;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;
import com.rcszh.lowcode.salary.util.SalaryParser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryService {
    @Resource
    private SalaryConfigService salaryConfigService;
    @Resource
    private SalaryMapper salaryMapper;
    @Resource
    private FakeUserService fakeUserService;

    /**
     * 生成薪资列表
     */
    public List<Salary> generateSalaryList(SalaryQueryParamDto salaryQueryParamDto) {
        // 拿到配置信息
        List<SalaryConfig> configs =  salaryConfigService.getConfigList();
        List<SalaryDataSchema> salaryDataSchemas = SalaryParser.parserList(configs);
        // TODO 去获取考勤信息
        List<FakeUser> user = fakeUserService.getAllUser();
        List<Salary> result = new ArrayList<>();
        for (FakeUser fakeUser : user) {
            String userId = fakeUser.getId();
            Salary salary = new Salary();
            salary.setUserId(userId);
            salary.setData(JSONUtil.toJsonStr(salaryDataSchemas));
            result.add(salary);
        }
        return result;
    }
}
