package com.credit.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.credit.common.result.ResultJson;
import com.credit.common.util.servlet.ResponseUtil;
import com.credit.entity.SiteResource;
import com.credit.entity.UserPermission;
import com.credit.service.UserPermissionService;

/**
 * Created by Michael Chan on 3/31/2017.
 */
public class AccessLimitInterceptor extends HandlerInterceptorAdapter
{
    public static final Logger logger = LoggerFactory.getLogger(AccessLimitInterceptor.class);

    @Resource
    private UserPermissionService<UserPermission> userPermissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String vKeyValues = request.getParameter("vkey");
        String tValues = request.getParameter("t");
        String requestUri = request.getServletPath();
        if (!tValues.trim().equals(""))
        {
            requestUri += "?t=" + tValues;
        }

        SiteResource siteResource = userPermissionService.searchSiteResourceByVkeyAndPath(vKeyValues, requestUri);
        if (siteResource == null || !StringUtils.equals(siteResource.getResourcePath(), requestUri))
        {
            ResponseUtil.printJsonString(response, new ResultJson().notAuth("很抱歉，您所请求的接口还未被授权。"));
            return false;
        }
        if (SiteResource.RESOURCE_STATUS_DELETE == siteResource.getResourceStatus())
        {
            ResponseUtil.printJsonString(response, new ResultJson().notAuth("此接口已关闭"));
            return false;
        }
        return true;

    }

}
