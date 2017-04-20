package com.credit.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.entity.User;
import com.credit.service.UserService;

/**
 * Created by wangjunling on 2017/3/14.
 */
public class BlackboxVkeyInterceptor extends HandlerInterceptorAdapter
{
    @Resource
    private UserService<User> userService;

    private Integer validatevKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (validatevKey == 0)
        {
            return true;
        }
        String vKeyValues = request.getParameter("vkey");
        User user = null;
        if (StringUtils.isNotBlank(vKeyValues))
        {
            user = userService.searchByVkey(vKeyValues);
        }
        if (StringUtils.isBlank(vKeyValues) || user == null)
        {
            PrintWriter writer = null;
            try
            {
                writer = response.getWriter();
                writer.write(JSON.toJSONString(new ResultJson().notAuth("vkey访问不正确")));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (writer != null)
                    IOUtils.closeQuietly(writer);
            }
            return false;
        }
        return true;
    }


    public Integer getValidatevKey()
    {
        return validatevKey;
    }

    public void setValidatevKey(Integer validatevKey)
    {
        this.validatevKey = validatevKey;
    }

}