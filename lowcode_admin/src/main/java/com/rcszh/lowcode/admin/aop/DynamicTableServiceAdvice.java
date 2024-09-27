package com.rcszh.lowcode.admin.aop;

import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.admin.service.DynamicTableService;
import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.enums.action.FormActionAssignmentModeEnum;
import com.rcszh.lowcode.core.enums.action.FormConditionTypeEnum;
import com.rcszh.lowcode.core.schema.FormActionConfigSchema;
import com.rcszh.lowcode.core.service.action.FormActionService;
import com.rcszh.lowcode.core.service.action.FormConditionService;
import com.rcszh.lowcode.core.service.form.FormTableService;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Aspect
@Component
public class DynamicTableServiceAdvice {
    @Resource
    private FormConditionService formConditionService;
    @Resource
    private FormActionService formActionService;
    @Resource
    private DynamicTableService dynamicTableService;
    @Resource
    private FormTableService formTableService;
    // 定义一个切点：所有被GetMapping注解修饰的方法会织入advice
    @Pointcut("execution(* com.rcszh.lowcode.admin.service.DynamicTableService.doActionToDynamicTable(..))")
    public void pointCut() {}

    // Before表示logAdvice将在目标方法执行前执行
    @Before("pointCut()")
    public void beforeAction(){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("get请求的advice触发了");
    }

    /**
     * TODO 这里有个坑，就是如果配置的动作是监听自己的insert，结果动作也是insert，岂不是会无线套娃
     */
    @SuppressWarnings("unchecked")
    @After("pointCut()")
    public void afterAction(JoinPoint joinPoint){
        // 后置
        // 操作的表单
        String formTableName = (String) joinPoint.getArgs()[0];
        // 操作类型
        String actionType = (String) joinPoint.getArgs()[1];
        // 操作数据
        HashMap<String,Object> actionInfo = (HashMap<String, Object>) joinPoint.getArgs()[2];
        FormTable formTable = formTableService.getFormTableByTableName(formTableName);
        String formId = formTable.getFormId();
        FormConditionTypeEnum conditionTypeEnum = FormConditionTypeEnum.covert(actionType);
        if (conditionTypeEnum == null) {
            throw new RuntimeException("不存在条件和动作的映射");
        }
        List<FormCondition> condition = formConditionService.findConditionByFormIdAndType(formId,conditionTypeEnum.getType());
        HashMap<String, List<FormAction>> actionByConditionToMap = formActionService.findActionByConditionToMap(condition.stream().map(FormCondition::getId).toList());
        for (FormCondition formCondition : condition) {
            String id = formCondition.getId();
            List<FormAction> actions = actionByConditionToMap.get(id);
            if(actions!=null){
                // 不为空证明当前条件有配置动作
                for (FormAction action : actions) {
                    String acType = action.getAcType();
                    String acTarget = action.getAcTarget();
                    FormActionConfigSchema formActionConfigSchema = JSONUtil.toBean(action.getAcConfig(), FormActionConfigSchema.class);
                    HashMap<String, Object> actionValue = formActionConfigSchema.getDataList().stream().reduce(new HashMap<String, Object>(), (prev, curr) -> {
                        String assignmentMode = curr.getAssignmentMode();
                        if (FormActionAssignmentModeEnum.FIXED_VALUE.getMode().equals(assignmentMode)){
                            prev.put(curr.getFieldName(), curr.getFieldValue());
                        }else if(FormActionAssignmentModeEnum.FORMULA_VALUE.getMode().equals(assignmentMode)){
                            prev.put(curr.getFieldName(), actionInfo.get(curr.getFieldValue()));
                        }else if(FormActionAssignmentModeEnum.NULL_VALUE.getMode().equals(assignmentMode)){
                            prev.put(curr.getFieldName(), actionInfo.get(curr.getFieldValue()));
                        }
                        return prev;
                    }, (l, r) -> l);
                    dynamicTableService.doActionToDynamicTable(acTarget,acType,actionValue);
                }
            }
        }
    }

}

