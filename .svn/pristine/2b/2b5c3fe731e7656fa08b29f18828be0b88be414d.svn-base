package com.credit.common.sms.ums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.credit.common.sms.ISmsEntry;
import com.credit.common.sms.SmsSender;
import com.credit.common.util.time.TimeUtil;

public class UmsSender implements SmsSender
{
    private String sendUrl;

    private String companyCode;

    private String smsUsername;

    private String smsPassword;

    private HttpClient httpClient;

    public UmsSender(String sendUrl, String companyCode, String smsUsername, String smsPassword, HttpClient httpClient)
    {
        this.sendUrl = sendUrl;
        this.companyCode = companyCode;
        this.smsUsername = smsUsername;
        this.smsPassword = smsPassword;

        if (null == httpClient)
        {
            httpClient = new HttpClient();
        }
        this.httpClient = httpClient;
        this.httpClient.getHostConfiguration().setHost("www.ums86.com:8899");
    }

    public String getSendUrl()
    {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl)
    {
        this.sendUrl = sendUrl;
    }

    public String getCompanyCode()
    {
        return companyCode;
    }

    public void setCompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public String getSmsUsername()
    {
        return smsUsername;
    }

    public void setSmsUsername(String smsUsername)
    {
        this.smsUsername = smsUsername;
    }

    public String getSmsPassword()
    {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword)
    {
        this.smsPassword = smsPassword;
    }

    public HttpClient getHttpClient()
    {
        return httpClient;
    }

    @Override
    public Map<String, String> send(ISmsEntry sms) throws Exception
    {
        UmsSmsEntry umsSms = (UmsSmsEntry) sms;
        String result = doGetRequest(getRequestUrl(umsSms));

        return parseResult(result);
    }

    private Map<String, String> parseResult(String result)
    {
        if (StringUtils.isBlank(result))
        {
            return null;
        }
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("requestResult", result);
        String[] resultArray = result.split("&");
        for (String resultPair : resultArray)
        {
            String[] pairs = resultPair.split("=");
            if (pairs.length < 1)
            {
                continue;
            }
            else
                if (pairs.length == 1)
                {
                    resultMap.put(pairs[0], "");
                }
                else
                {
                    resultMap.put(pairs[0], pairs[1]);
                }
        }

        return resultMap;
    }

    private String getRequestUrl(UmsSmsEntry umsSms) throws Exception
    {
        String time = "";
        if (null != umsSms.getScheduleTime())
        {
            time = TimeUtil.format(umsSms.getScheduleTime(), TimeUtil.FORMAT_COMPACT);
        }

        String url = sendUrl + "?SpCode=" + getCompanyCode() + "&LoginName=" + getSmsUsername() + "&Password="
                + getSmsPassword() + "&MessageContent=" + URLEncoder.encode(umsSms.getContent(), "GB2312")
                + "&UserNumber=" + umsSms.getReceiver() + "&SerialNumber=" + umsSms.getSerialNumber()
                + "&ScheduleTime=" + time + "&f=1";

        return url;
    }

    private String doGetRequest(String url) throws Exception
    {
        GetMethod method = null;

        try
        {
            method = new GetMethod(url);
            setRequestHeaders(method);

            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
            httpClient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 300000);
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK)
            {
                return null;
            }
            return convertStreamToString(method.getResponseBodyAsStream());
        }
        finally
        {
            if (null != method)
            {
                method.releaseConnection();
            }
        }
    }

    private String convertStreamToString(InputStream stream) throws Exception
    {
        if (null == stream)
        {
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "GB2312"));
        StringBuffer resBuffer = new StringBuffer();
        String resTemp = "";
        while ((resTemp = br.readLine()) != null)
        {
            resBuffer.append(resTemp);
        }

        stream.close();
        br.close();
        return resBuffer.toString();
    }

    private void setRequestHeaders(HttpMethodBase method)
    {
        if (null != method)
        {
            method.addRequestHeader("Connection", "keep-alive");
            method.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
        }
    }

    private NameValuePair[] getPostParams(UmsSmsEntry umsSms) throws Exception
    {
        String time = "";
        if (null != umsSms.getScheduleTime())
        {
            time = TimeUtil.format(umsSms.getScheduleTime(), TimeUtil.FORMAT_COMPACT);
        }

        System.out.println(URLEncoder.encode(umsSms.getContent(), "GB2312"));
        return new NameValuePair[]{new NameValuePair("SpCode", getCompanyCode()),
                new NameValuePair("LoginName", getSmsUsername()), new NameValuePair("Password", getSmsPassword()),
                new NameValuePair("MessageContent", umsSms.getContent()),
                new NameValuePair("UserNumber", umsSms.getReceiver()),
                new NameValuePair("SerialNumber", umsSms.getSerialNumber()), new NameValuePair("ScheduleTime", time),
                new NameValuePair("f", "1")};
    }

    /**
     * 发送post请求
     * 
     * @param url
     *            请求地址
     * @return
     */
    private String doPostRequest(String url, NameValuePair[] params) throws Exception
    {
        PostMethod method = null;
        try
        {
            HttpClient httpClient = getHttpClient();
            httpClient.getState().clear();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
            httpClient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 300000);

            method = new PostMethod(url);

            // 设置请求头部信息
            setRequestHeaders(method);

            method.addParameters(params);

            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK)
            {
                return null;
            }

            InputStream is = method.getResponseBodyAsStream();

            return convertStreamToString(is);

        }
        catch (HttpException e)
        {
            throw e;
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (null != method)
            {
                method.releaseConnection();
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(httpClient.getParams().USER_AGENT,
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

        UmsSender sender = new UmsSender("http://www.ums86.com:8899/sms/Api/Send.do", "008673", "shpingan",
                "utn20121118", httpClient);
        UmsSmsEntry entry = new UmsSmsEntry();
        // entry.setContent("恭喜您，您的网站已完成认证，网站认证信息已在可信终端展示，再次感谢您对互联网诚信事业的支持。");
        // entry.setContent("您的网站张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张张.com申请的实名网站认证信息或资料填写有误，请登录网站认证申请系统进行查看和修改操作。");
        entry.setContent("恭喜您，您的网站已完成认证，网站认证信息已在可信终端展示，再次感谢您对互联网诚信事业的支持。");
        entry.setContent("恭喜您张,您的网站apply.trustutn.org已通过复审审核，认证标识已签发，请登陆网站认证申请系统完成后续操作。");
        entry.setReceiver("13020102658,13166478030,13262826516,13382519853,13472873780,13501735336,13761860845,13816008161,13960773873,15000792746,15216780622,15721069008,15921567586,18017589568,18101671100,18217646829,18317170798,18516210366,18621510408,18721846037,18801963989,18918695232");
        entry.setSerialNumber("20140723111359123473");
        System.out.println(sender.send(entry));

        System.out.println(URLEncoder.encode(entry.getContent(), "GB2312"));
    }
}
