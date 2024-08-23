package com.rcszh.lowcode.common.controller;

import com.github.pagehelper.PageHelper;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import com.rcszh.lowcode.common.entity.LoginUser;
import com.rcszh.lowcode.common.entity.RequestPageInfo;
import com.rcszh.lowcode.common.module.TableSupport;
import com.rcszh.lowcode.common.utils.SecurityUtils;
import com.rcszh.lowcode.common.vo.BaseResponse;

public class BaseController {
    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        RequestPageInfo requestPageInfo = TableSupport.getRequestPageInfo();
        Integer pageNum = requestPageInfo.getPageNum();
        Integer pageSize = requestPageInfo.getPageSize();
        if (pageNum != null  && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    protected void notSupAdminSetTenant(BaseTenantEntity tenantEntity){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (!SecurityUtils.isAdmin(loginUser.getUserId(),loginUser.getUserType())){
            tenantEntity.setTenantId(SecurityUtils.getTenantId());
        }
    }

    /**
     * 返回成功消息
     */
    public BaseResponse success(Object data) {
        return BaseResponse.success(data);
    }
    /**
     * 返回失败消息
     */
    public BaseResponse error(String message)
    {
        return BaseResponse.error(message);
    }
}
