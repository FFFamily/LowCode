//package com.rcszh.lowcode.orm;
//
//import cn.hutool.extra.spring.SpringUtil;
//import com.rcszh.lowcode.orm.entity.FilterCondition;
//import com.rcszh.lowcode.orm.module.SqlUtil;
//import com.rcszh.lowcode.orm.params.SelectParam;
//import lombok.Setter;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//public interface ORMInterface {
//    private static final String KEY_PREFIX = "${";
//    private static final String KEY_SUFFIX = "!}";
//    private static final String ID_KEY = "${id!}";
//    private static final String TABLE_NAME_KEY ="${tableName!}";
//    private static final String COLUMN_NAME_KEY = "${columnName!}";
//    private static final String COLUMN_VALUE_KEY = "${columnValue!}";
//    private static final String COLUMN_KEY = "${column!}";
//    private static final String QUERY_KEY = "${query!}";
//    // 生成业务模型对应的数据库表模版
//    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_KEY+ """
//            (\s
//            id varchar(50) not null primary key ,"""
//            + COLUMN_NAME_KEY+
//            """
//            create_by varchar(50) null  ,
//            create_at varchar(50) null  ,
//            update_by varchar(50) null  ,
//            update_at varchar(50) null
//            )
//           \s
//           \s""" ;
//    // 查询
////    private static final String SELECT_ALL_SQL = "SELECT "+COLUMN_KEY+" FROM "+ TABLE_NAME_KEY;
//    private static final String SELECT_ALL_SQL = "SELECT * FROM "+ TABLE_NAME_KEY;
//    private static final String BASE_SELECT_SQL = "SELECT "+COLUMN_KEY+" FROM "+ TABLE_NAME_KEY +" WHERE "+QUERY_KEY;
//    // 更新
//    private static final String UPDATE_ONE_SQL = "UPDATE "+TABLE_NAME_KEY+" SET "+COLUMN_NAME_KEY+"="+COLUMN_VALUE_KEY+" WHERE id = "+ID_KEY;
//    // 添加
//    private static final String INSERT_ONE_SQL = "INSERT INTO "+TABLE_NAME_KEY+" ("+COLUMN_NAME_KEY+") VALUES ("+COLUMN_VALUE_KEY+")";
//    // 删除
//    private static final String DELETE_ONE_BY_ID_SQL = "DELETE FROM "+TABLE_NAME_KEY+" WHERE id= "+COLUMN_VALUE_KEY;
//
//    private JdbcTemplate jdbcTemplate;
//    /**
//     * 表名
//     */
//    private String tableName;
//    /**
//     * 查询条件
//     */
//    private String queryWhere;
//    /**
//     * 表字段
//     */
//    private String columns;
//    /**
//     * key映射
//     */
//    private HashMap<String,String> mapping;
//
//    /**
//     * 构建
//     */
//    public static ORM orm(){
//        ORM orm = new ORM();
////        orm.setJdbcTemplate(SpringUtil.getBean(JdbcTemplate.class));
////        orm.setMapping(new HashMap<>());
////        // 默认查询所有字段
////        orm.setColumns("*");
//        return orm;
//    }
//
//    /**
//     * 条件
//     */
//    public ORM where(List<FilterCondition> filterConditions){
//        this.queryWhere = filterConditions.stream().map(SqlUtil::convertFilterConfigToSql).collect(Collectors.joining("AND "));
//        return this;
//    }
//
//    /**
//     * 列
//     */
//    public ORM columns(List<String> column){
//        this.columns = String.join(",",column);
//        return this;
//    }
//
//    public ORM tableName(String tableName) {
//        String regex = "^\\w+$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(tableName);
//        if (!matcher.matches()) {
//            throw new RuntimeException("错误的表信息");
//        }
//        this.tableName = tableName;
//        return this;
//    }
//
//    @Override
//    public <T> List<T> selectList(SelectParam<T> param) {
//        return List.of();
//    }
//
//    @Override
//    public <T> T selectOne(SelectParam<T> param) {
//        return null;
//    }
//
//    @Override
//    public Map<String, Object> selectOneByIdWithMap(String fieldId) {
//        return Map.of();
//    }
//
//    @Override
//    public void insertWithMap(Map<String, Object> tableInfo) {
//
//    }
//
//
//    /**
//     * 创建基础的模版数据表
//     */
//    public void createTemplateTable(String primaryKey){
//        mapping.put(TABLE_NAME_KEY, tableName);
//        if (primaryKey != null){
//            mapping.put(COLUMN_NAME_KEY, primaryKey+"_id"+" varchar(50) not null ,");
//        }else {
//            mapping.put(COLUMN_NAME_KEY, "");
//        }
//        String sql = SqlUtil.replaceSQL(CREATE_TABLE_SQL, mapping);
//        System.out.println("执行的SQL："+sql);
//        jdbcTemplate.execute(sql);
//    }
//
//    /**
//     * 创建基础的模版数据表
//     */
//    public void updateTable(List<SqlFieldConfig> sqlFieldConfigs){
//        if (sqlFieldConfigs == null || sqlFieldConfigs.isEmpty()){
//            return;
//        }
//        mapping.put(TABLE_NAME_KEY, tableName);
//        String Alter_TABLE_SQL = "ALTER TABLE";
//        String ADD_FIELD_SQL = "ADD ";
//        String collect = sqlFieldConfigs.stream().map(item -> ADD_FIELD_SQL+" "+SqlUtil.convertFieldConfigToSql(item)).collect(Collectors.joining(","));
//        String sql = Alter_TABLE_SQL+" "+tableName+" "+collect;
//        System.out.println("执行的SQL："+sql);
//        jdbcTemplate.execute(sql);
//    }
//
//    /**
//     * 查询所有的数据信息
//     */
//    public List<Map<String, Object>> selectList(){
//        mapping.put(TABLE_NAME_KEY, tableName);
//        String sql = SqlUtil.replaceSQL(SELECT_ALL_SQL, mapping);
//        System.out.println("执行的SQL："+sql);
//        return jdbcTemplate.queryForList(sql);
//    }
//
//
//
//
//    /**
//     * 添加单个数据
//     * @param tableInfo 表数据
//     */
//    public void insert(Map<String, Object> tableInfo) {
//        mapping.put(TABLE_NAME_KEY, tableName);
//        mapping.put(COLUMN_NAME_KEY, String.join(",", tableInfo.keySet()));
//        mapping.put(COLUMN_VALUE_KEY,tableInfo.values().stream().map(Object::toString).collect(Collectors.joining(",")));
//        String sql = SqlUtil.replaceSQL(INSERT_ONE_SQL, mapping);
//        System.out.println("执行的SQL："+sql);
//        jdbcTemplate.execute(sql);
//    }
//
//    /**
//     * 更新单个数据
//     * @param tableInfo 表数据
//     */
//    public void update(Map<String, Object> tableInfo){
//        mapping.put(TABLE_NAME_KEY, tableName);
//        mapping.put(COLUMN_NAME_KEY, String.join(",", tableInfo.keySet()));
//        mapping.put(COLUMN_VALUE_KEY,tableInfo.values().stream().map(Object::toString).collect(Collectors.joining(",")));
//        mapping.put(ID_KEY,tableInfo.get("id").toString());
//        String sql = SqlUtil.replaceSQL(UPDATE_ONE_SQL, mapping);
//        System.out.println("执行的SQL："+sql);
//        jdbcTemplate.execute(sql);
//    }
//
//    @Override
//    public void deleteById(String fieldId) {
//
//    }
//
//    /**
//     * 删除指定id数据
//     * @param fieldId 表Id
//     */
//    public void deletedById(String fieldId) {
//        mapping.put(TABLE_NAME_KEY, tableName);
//        mapping.put(COLUMN_VALUE_KEY, fieldId);
//        String sql = SqlUtil.replaceSQL(DELETE_ONE_BY_ID_SQL, mapping);
//        System.out.println("执行的SQL："+sql);
//        jdbcTemplate.execute(sql);
//    }
//
//    /**
//     * @param fieldId 表id
//     * @return 获取指定Id数据
//     */
//    public Map<String, Object> selectOneById(String fieldId) {
//        mapping.put(TABLE_NAME_KEY, tableName);
//        HashMap<String , String> map = new HashMap<>();
//        map.put("id", fieldId);
//        String sql = SqlUtil.replaceSQL(SELECT_ALL_SQL+SqlUtil.buildQuerySqlByParams(map), mapping);
//        System.out.println("执行的SQL："+sql);
//        try {
//            return jdbcTemplate.queryForMap(sql);
//        }catch (EmptyResultDataAccessException e){
//            return null;
//        }
//    }
//
//    /**
//     * 指定类型查询
//     */
//    public <T> T selectOneForObject( Class<T> tClass) {
//        mapping.put(TABLE_NAME_KEY, tableName);
//        mapping.put(COLUMN_KEY, columns);
//        mapping.put(QUERY_KEY, queryWhere);
//        String sql = SqlUtil.replaceSQL(BASE_SELECT_SQL, mapping);
//        return jdbcTemplate.queryForObject(sql, tClass);
//    }
//}
