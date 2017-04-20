package com.credit.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.credit.entity.AccessLog;
import com.credit.service.AccessLogService;

/**
 * Created by Michael Chan on 3/31/2017.
 */
public final class AccessLogFilter implements Filter
{
    public static final Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    private AccessLogService<AccessLog> accessLogService;

    // 用于创建MultipartHttpServletRequest
//    private MultipartResolver multipartResolver = null;

    @Override
    public void init(FilterConfig filterConfig)
    {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        // 注入bean
//        multipartResolver = (MultipartResolver) ctx.getBean("multipartResolver");
        accessLogService = (AccessLogService) ctx.getBean("accessLogService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        HttpServletRequest request1 = (HttpServletRequest) request;
        try
        {
            chain.doFilter(request, responseWrapper);
//            String userName = UserSessionFactory.getUserSession(request).getUserName();
                String vKeyValues = request1.getParameter("vkey");
                String tValues = request.getParameter("t");
                Map params = request.getParameterMap();
                String requestUri = request1.getServletPath();
            if (!tValues.trim().equals(""))
                {
                    requestUri += "?=" + tValues;
                }
                String responseContentType = response.getContentType();
                AccessLog accessLog = new AccessLog();
//            accessLog.setUserName(userName);
                accessLog.setvKey(vKeyValues);
                accessLog.setResourceName(requestUri);
                accessLog.setParams(JSON.toJSONString(params));
                accessLog.setResponseType(responseContentType);
                accessLog.setResponseResult(new String(responseWrapper.getDataStream(), "utf-8"));
                accessLog.setRequestTime(new Date());
                accessLog.setCreateTime(new Date());
                accessLog.setUpdateTime(new Date());
                accessLogService.saveAccessRecord(accessLog);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
            response.getOutputStream().write(responseWrapper.getDataStream());
        }

    @Override
    public void destroy(){}
}