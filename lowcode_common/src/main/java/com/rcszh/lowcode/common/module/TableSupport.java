package com.rcszh.lowcode.common.module;

import com.rcszh.lowcode.common.entity.RequestPageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

public class TableSupport {
    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_SIZE = "pageSize";
    /**
     * 获取当前请求的分页信息
     */
    public static RequestPageInfo getRequestPageInfo() {
        RequestPageInfo requestPageInfo = new RequestPageInfo();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Optional.ofNullable(request.getParameter(PAGE_NUM))
                        .ifPresent(value -> requestPageInfo.setPageNum(Integer.parseInt(value)));
        Optional.ofNullable(request.getParameter(PAGE_SIZE))
                        .ifPresent(value -> requestPageInfo.setPageSize(Integer.parseInt(value)));
        return requestPageInfo;
    }
}
