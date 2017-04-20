package com.credit.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.web.util.LicenseHelp;

/**
 * Created by wangjunling on 2017/3/14.
 */
public class BlackboxLicenseInterceptor extends HandlerInterceptorAdapter
{
    private String encryLicense;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Long datetime = Long.parseLong(LicenseHelp.decryLicense(encryLicense));
        if (datetime - System.currentTimeMillis() <= 0)
        {
            PrintWriter writer = null;
            try
            {
                writer = response.getWriter();
                writer.write(JSON.toJSONString(new ResultJson().requestExpire()));
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
			return false;
        }
		return true;
    }

    public String getEncryLicense()
    {
        return encryLicense;
    }

    public void setEncryLicense(String encryLicense)
    {
        this.encryLicense = encryLicense;
    }
}
