package com.rcszh.lowcode.core.service;

import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceTable;
import com.rcszh.lowcode.core.mapper.CoreDataSourceTableMapper;
import jakarta.annotation.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据表
 * 业务模型
 */
@Service
public class CoreDataSourceTableService {
    @Resource
    private CoreDataSourceTableMapper coreDataSourceTableMapper;
    @Resource
    private JdbcTemplate jdbcTemplate;
    // 生成业务模型对应的数据库表
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ${tableName!} ( id varchar(50) not null primary key ) ";
    private static final String TABLE_ID = "id varchar(50) not null primary key";

    @Resource
    private CoreDataSourceTableRuleConfigService coreDataSourceTableRuleConfigService;

    /**
     * 创建业务模型
     */
    public void createOneDataSourceTable(CoreDataSourceTable coreDataSourceTable) {
        coreDataSourceTableMapper.insert(coreDataSourceTable);
        HashMap<String,String> replaceMap = new HashMap<>();
        replaceMap.put("tableName", coreDataSourceTable.getTableName());
        String sql = replaceSQL(CREATE_TABLE_SQL, replaceMap);
        jdbcTemplate.execute(sql);
    }


    /**
     * 获取真实的业务模型列表数据
     */
    public void getRealTableDataList(String tableName) {

    }

    /**
     * 删除业务模型
     */
    public void deleteById(String id) {

        coreDataSourceTableMapper.deleteById(id);
    }

    /**
     * 更新业务模型
     */
    public void update(CoreDataSourceTable coreDataSourceTable) {
        coreDataSourceTableMapper.updateById(coreDataSourceTable);
    }

    /**
     * 通过Id查询业务模型
     */
    public  CoreDataSourceTable getTableById(String id) {
        return coreDataSourceTableMapper.selectById(id);
    }



    public Object getAllDataSource(Long sourceId) {
        return coreDataSourceTableMapper.selectList(new LambdaQueryWrapper<CoreDataSourceTable>().eq(CoreDataSourceTable::getCodeDataSourceId, sourceId));
    }

    private String replaceSQL(String templateSql, HashMap<String,String> map) {
        Pattern TEMPLATE_ARG_PATTERN = Pattern.compile("\\$\\{(.+?)!}"); // ${param!}
        Matcher m = TEMPLATE_ARG_PATTERN.matcher(templateSql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String arg = m.group(1);
            String replaceStr = map.get(arg);
            m.appendReplacement(sb, replaceStr != null ? replaceStr : "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
