package com.rcszh.lowcode.orm;

import com.rcszh.lowcode.orm.constants.SqlConstant;
import com.rcszh.lowcode.orm.exceptions.OrmBuilderException;
import com.rcszh.lowcode.orm.factory.OrmConfig;
import com.rcszh.lowcode.orm.mapper.AbstractBaseORM;
import com.rcszh.lowcode.orm.module.SqlUtil;
import com.rcszh.lowcode.orm.params.SelectWrapper;
import com.rcszh.lowcode.orm.params.SelectWrapperUtil;
import lombok.Getter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ORM builder 构建器 类似 SqlSession
 * TODO sql注入问题要解决
 */
public class ORM extends AbstractBaseORM {
    private final OrmConfig config;

    public ORM(OrmConfig config) {
        this.config = config;
    }

    @Override
    public List<Map<String, Object>> selectListToMap(SelectWrapper wrapper) {
        if (wrapper == null) {
            wrapper = new SelectWrapper();
        }
        HashMap<String,String> mapping = new HashMap<>();
        mapping.put(SqlConstant.TABLE_NAME_KEY, config.getTableName());
        mapping.put(SqlConstant.COLUMN_KEY,config.getSelectFields() == null ?  "*" : String.join(",", config.getSelectFields()));
        String selectSQL = SqlUtil.replaceSQL(SqlConstant.SELECT_SQL,mapping);
        String whereParam = SelectWrapperUtil.createWhereParam(wrapper);
        JdbcTemplate jdbcTemplate = config.getJdbcTemplate();
        String sql = selectSQL+whereParam;
        List<Object> collect = wrapper.getQueryList().stream().map(SelectWrapper.SelectParam::getValue).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return jdbcTemplate.queryForList(sql);
        }
        return jdbcTemplate.queryForList(sql,collect);
    }

    @Override
    public Map<String, Object> selectOneToMap(SelectWrapper wrapper) {
        List<Map<String, Object>> list = selectListToMap(wrapper);
        if (list.size() > 1){
            throw new RuntimeException("查询SQL期望数量为1,结果为: "+list.size());
        }
        return list.getFirst();
    }

    @Override
    public Map<String, Object> selectByIdToMap(String fieldId) {
        return selectOneToMap(new SelectWrapper().eq(SqlConstant.TABLE_ID,fieldId));
    }

    @Override
    public void insertByMap(Map<String, Object> tableInfo) {

    }

    @Override
    public void update(Map<String, Object> tableInfo) {

    }

    @Override
    public void deleteById(String fieldId) {

    }

    @Override
    public void updateTable(List<SqlFieldConfig> sqlFieldConfigs) {

    }
}
