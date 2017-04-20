package com.credit.common.util.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

/**
 * Created by wangjunling on 2017/4/7.
 */
public class ResponseUtil
{
    public static void printJsonString(HttpServletResponse response, Object result)
    {
        PrintWriter writer = null;
        try
        {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=UTF-8");
			writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
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
    }
}
