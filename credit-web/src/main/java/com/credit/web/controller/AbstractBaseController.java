package com.credit.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.credit.common.cache.ICache;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.common.util.servlet.RequestUtil;
import com.credit.common.web.session.UserSession;
import com.credit.common.web.session.UserSessionFactory;
import com.credit.entity.User;
import com.credit.service.UserService;

/**
 * Created by Michael Chan on 3/8/2017.
 */
public abstract class AbstractBaseController extends AbstractController
{
    @Autowired
    UserService userService;

    protected Logger logger = null;

    public AbstractBaseController()
    {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        return null;
    }

    protected void setUserSession(User user, HttpServletRequest request) throws Exception
    {
        user = userService.updateUserLoginTime(user);
        UserSession userSession = new UserSession();
        userSession.setIp(RequestUtil.getRequestIP(request));
        BeanUtils.copyProperties(userSession, user);
        UserSessionFactory.updateUserSession(request.getSession(), userSession);
    }

    @ExceptionHandler
    public String exp(HttpServletRequest request, HttpServletResponse response, Exception ex)
    {
        logger.error("error", ex);
        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestType))
        {
            printAjaxResult(response);
        }
        return "500";
    }

    private void printAjaxResult(HttpServletResponse response)
    {
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();

            writer.write(JSON.toJSONString(new ResultJson().error()));
        }
        catch (IOException e)
        {
			logger.error("error",e);
        }
        finally
        {
            if (writer != null)
                IOUtils.closeQuietly(writer);
        }
    }

    boolean isFluentQuery(ICache<String, Integer> ipCache, String ip, Integer limitedQueryTimes) throws Exception
    {
        Integer value = ipCache.get(ip);
        if (value != null)
        {
            if (value > limitedQueryTimes - 1)
            {
                return true;
            }
            else
            {
                ipCache.cache(ip, ++value);
                return false;
            }
        }
        ipCache.cache(ip, 1);
        return false;
    }
}
