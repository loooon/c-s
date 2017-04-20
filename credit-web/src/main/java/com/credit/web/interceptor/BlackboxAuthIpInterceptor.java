package com.credit.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.credit.service.cache.UserAuthIpCache;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.common.util.servlet.RequestUtil;
import com.credit.entity.UserAuthIp;
import com.credit.service.UserAuthIpService;

/**
 * Created by wangjunling on 2017/3/14.
 */
public class BlackboxAuthIpInterceptor extends HandlerInterceptorAdapter
{


    private Integer validateIpAuth;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (validateIpAuth == 0)
        {
            return true;
        }
        String ip = RequestUtil.getRequestIP(request);
        String VKEY = "vkey";
        String vKeyValues = request.getParameter(VKEY);
		List<String> ipList = UserAuthIpCache.getInstance().getIPList(vKeyValues);
        if (StringUtils.isBlank(vKeyValues) || ipList==null || !ipList.contains(ip))
        {
            PrintWriter writer = null;
            try
            {
                writer = response.getWriter();
                writer.write(JSON.toJSONString(new ResultJson().notAuth()));
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

    public Integer getValidateIpAuth()
    {
        return validateIpAuth;
    }

    public void setValidateIpAuth(Integer validateIpAuth)
    {
        this.validateIpAuth = validateIpAuth;
    }
}