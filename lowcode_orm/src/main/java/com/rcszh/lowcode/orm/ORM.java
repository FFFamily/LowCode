package com.rcszh.lowcode.orm;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.hash.Hash;
import cn.hutool.extra.spring.SpringUtil;
import com.rcszh.lowcode.orm.module.SqlUtil;
import jakarta.annotation.Resource;
import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ORM builder 构建器
 * TODO sql注入问题要解决
 */
@Component
public class ORM {
    private static final String KEY_PREFIX = "${";
    private static final String KEY_SUFFIX = "!}";
    private static final String ID_KEY = KEY_PREFIX+"id"+KEY_SUFFIX;
    private static final String TABLE_NAME_KEY = KEY_PREFIX+"tableName"+KEY_SUFFIX;
    private static final String COLUMN_NAME_KEY = KEY_PREFIX+"columnName"+KEY_SUFFIX;
    private static final String COLUMN_VALUE_KEY = KEY_PREFIX+"columnValue"+KEY_SUFFIX;
    // 生成业务模型对应的数据库表
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_KEY+" ( id varchar(50) not null primary key ) ";
    // 查询
    private static final String SELECT_ALL_SQL = "SELECT * FROM "+ TABLE_NAME_KEY;
    // 更新
    private static final String UPDATE_ONE_SQL = "UPDATE "+TABLE_NAME_KEY+" SET "+COLUMN_NAME_KEY+"="+COLUMN_VALUE_KEY+" WHERE id = "+ID_KEY;
    // 添加
    private static final String INSERT_ONE_SQL = "INSERT INTO "+TABLE_NAME_KEY+" ("+COLUMN_NAME_KEY+") VALUES ("+COLUMN_VALUE_KEY+")";
    // 删除
    private static final String DELETE_ONE_BY_ID_SQL = "DELETE FROM "+TABLE_NAME_KEY+" WHERE id= "+COLUMN_VALUE_KEY;
    @Setter
    private JdbcTemplate jdbcTemplate;
    /**
     * 表名
     */
    private String tableName;
    /**
     * key映射
     */
    @Setter
    private HashMap<String,String> mapping;

    /**
     * 构建
     */
    public static ORM orm(){
        ORM orm = new ORM();
        orm.setJdbcTemplate(SpringUtil.getBean(JdbcTemplate.class));
        orm.setMapping(new HashMap<>());
        return orm;
    }




    public ORM tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }


    /**
     * 创建基础的模版数据表
     */
    public void createTemplateTable(){
        mapping.put(TABLE_NAME_KEY, tableName);
        String sql = SqlUtil.replaceSQL(CREATE_TABLE_SQL, mapping);
        System.out.println("执行的SQL："+sql);
        jdbcTemplate.execute(sql);
    }

    /**
     * 查询所有的数据信息
     */
    public List<Map<String, Object>> selectList(){
        mapping.put(TABLE_NAME_KEY, tableName);
        String sql = SqlUtil.replaceSQL(SELECT_ALL_SQL, mapping);
        System.out.println("执行的SQL："+sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 添加单个数据
     * @param tableInfo 表数据
     */
    public void insert(HashMap<String, Object> tableInfo){
        mapping.put(TABLE_NAME_KEY, tableName);
        mapping.put(COLUMN_NAME_KEY, String.join(",", tableInfo.keySet()));
        mapping.put(COLUMN_VALUE_KEY,tableInfo.values().stream().map(Object::toString).collect(Collectors.joining(",")));
        String sql = SqlUtil.replaceSQL(INSERT_ONE_SQL, mapping);
        System.out.println("执行的SQL："+sql);
        jdbcTemplate.execute(sql);
    }

    /**
     * 更新单个数据
     * @param tableInfo 表数据
     */
    public void update(HashMap<String, Object> tableInfo){
        mapping.put(TABLE_NAME_KEY, tableName);
        mapping.put(COLUMN_NAME_KEY, String.join(",", tableInfo.keySet()));
        mapping.put(COLUMN_VALUE_KEY,tableInfo.values().stream().map(Object::toString).collect(Collectors.joining(",")));
        mapping.put(ID_KEY,tableInfo.get("id").toString());
        String sql = SqlUtil.replaceSQL(UPDATE_ONE_SQL, mapping);
        System.out.println("执行的SQL："+sql);
        jdbcTemplate.execute(sql);
    }

    /**
     * 删除指定id数据
     * @param fieldId 表Id
     */
    public void deletedById(String fieldId) {
        mapping.put(TABLE_NAME_KEY, tableName);
        mapping.put(COLUMN_VALUE_KEY, fieldId);
        String sql = SqlUtil.replaceSQL(DELETE_ONE_BY_ID_SQL, mapping);
        System.out.println("执行的SQL："+sql);
        jdbcTemplate.execute(sql);
    }

    /**
     * @param fieldId 表id
     * @return 获取指定Id数据
     */
    public Map<String, Object> selectOneById(String fieldId) {
        mapping.put(TABLE_NAME_KEY, tableName);
        HashMap<String , String> map = new HashMap<>();
        map.put("id", fieldId);
        String sql = SqlUtil.replaceSQL(SELECT_ALL_SQL+SqlUtil.buildQuerySqlByParams(map), mapping);
        System.out.println("执行的SQL："+sql);
        try {
            return jdbcTemplate.queryForMap(sql);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

}