package com.rcszh.lowcode.orm;

import cn.hutool.extra.spring.SpringUtil;
import com.rcszh.lowcode.orm.entity.FilterCondition;
import com.rcszh.lowcode.orm.module.SqlUtil;
import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface ORMInterface {

}
