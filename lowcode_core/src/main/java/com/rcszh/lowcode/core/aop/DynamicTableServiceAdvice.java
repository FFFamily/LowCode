package com.rcszh.lowcode.core.aop;

import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.service.action.FormActionService;
import com.rcszh.lowcode.core.service.action.FormConditionService;
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
    // 定义一个切点：所有被GetMapping注解修饰的方法会织入advice
    @Pointcut("execution(* com.mutest.controller..*.*(..))")
    public void pointCut() {}

    // Before表示logAdvice将在目标方法执行前执行
    @Before("pointCut()")
    public void beforeAction(){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("get请求的advice触发了");
    }
    @After("pointCut()")
    public void afterAction(JoinPoint joinPoint){
        // 后置
        String formId = (String) joinPoint.getArgs()[0];
        String actionType = (String) joinPoint.getArgs()[1];
        List<FormCondition> condition = formConditionService.findConditionByFormId(formId,actionType);
        HashMap<String, FormAction> actionByConditionToMap = formActionService.findActionByConditionToMap(condition.stream().map(FormCondition::getId).toList());
        for (FormCondition formCondition : condition) {

        }
    }

}

