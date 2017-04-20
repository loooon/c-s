package com.credit.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.entity.User;
import com.credit.service.UserService;
import com.credit.service.cache.UserInfoCache;

/**
 * Created by wangjunling on 2017/3/17.
 */
public class BlackboxUsageLimitInterceptor extends HandlerInterceptorAdapter
{
    public static final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);

    private Integer interfaceUsageLimitSwitch;

    private Integer callDetailMarkCount;

    private Integer dunNumberMarkCount;

    private Integer smsUploadTotal;

    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (interfaceUsageLimitSwitch == 0)
        {
            return true;
        }
        String t = request.getParameter("t");
        if (t == null)
        {
            print(response, new ResultJson().notAuth("接口错误"));
            return false;
        }
        String vkey = request.getParameter("vkey");
        User user = UserInfoCache.getInstance().getUser(vkey);
        if (user == null)
        {
            print(response, new ResultJson().paramError("vkey不正确"));
            return false;
        }
        switch (t)
        {
        case "call_detail_number_mark":
            if (user.getCallDetailMarkCount() >= callDetailMarkCount)
            {
                print(response, new ResultJson().notAuth("此接口使用次数已达上限"));
                return false;
            }
            break;
        case "dun_number_mark":
            if (user.getDunNumberMarkCount() >= dunNumberMarkCount)
            {
                print(response, new ResultJson().notAuth("此接口使用次数已达上限"));
                return false;
            }
            break;
        case "upload_text":
            if (user.getSmsUploadCount() >= smsUploadTotal)
            {
                print(response, new ResultJson().notAuth("此接口使用次数已达上限"));
                return false;
            }
            break;

        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        if (interfaceUsageLimitSwitch == 0)
        {
            return;
        }
        String t = request.getParameter("t");
        String vkey = request.getParameter("vkey");
        UserInfoCache userCache = UserInfoCache.getInstance();
        User user = userCache.getUser(vkey);
        switch (t)
        {
        case "call_detail_number_mark":
            user.setCallDetailMarkCount(user.getCallDetailMarkCount() + 1);
            userService.update(user);
            userCache.cache(user);
            break;
        case "dun_number_mark":
            user.setDunNumberMarkCount(user.getDunNumberMarkCount() + 1);
            userService.update(user);
            userCache.cache(user);
            break;
        case "upload_text":
            user.setSmsUploadCount(user.getSmsUploadCount() + 1);
            userService.update(user);
            userCache.cache(user);
            break;
        }
    }

    private void print(HttpServletResponse response, ResultJson resultJson)
    {
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(resultJson));
        }
        catch (IOException e)
        {
            // TODO ignore
        }
        finally
        {
            if (writer != null)
                IOUtils.closeQuietly(writer);
        }

    }

    public Integer getInterfaceUsageLimitSwitch()
    {
        return interfaceUsageLimitSwitch;
    }

    public void setInterfaceUsageLimitSwitch(Integer interfaceUsageLimitSwitch)
    {
        this.interfaceUsageLimitSwitch = interfaceUsageLimitSwitch;
    }

    public Integer getCallDetailMarkCount()
    {
        return callDetailMarkCount;
    }

    public void setCallDetailMarkCount(Integer callDetailMarkCount)
    {
        this.callDetailMarkCount = callDetailMarkCount;
    }

    public Integer getDunNumberMarkCount()
    {
        return dunNumberMarkCount;
    }

    public void setDunNumberMarkCount(Integer dunNumberMarkCount)
    {
        this.dunNumberMarkCount = dunNumberMarkCount;
    }

    public Integer getSmsUploadTotal()
    {
        return smsUploadTotal;
    }

    public void setSmsUploadTotal(Integer smsUploadTotal)
    {
        this.smsUploadTotal = smsUploadTotal;
    }

    public UserService getUserService()
    {
        return userService;
    }

    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }
}
