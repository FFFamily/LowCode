package com.rcszh.lowcode.core.mapper.form_rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rcszh.lowcode.core.entity.form_rule.FormRule;
import com.rcszh.lowcode.core.entity.form_rule.FormShowRule;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表单显示配置
 */
@Mapper
public interface FormShowRuleMapper extends BaseMapper<FormShowRule> {

}
