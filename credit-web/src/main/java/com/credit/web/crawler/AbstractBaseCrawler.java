package com.credit.web.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

public abstract class AbstractBaseCrawler<T> implements ICrawler<T>
{
    private HttpClient httpClient;

    private String cookie;

    private String referer;

    public AbstractBaseCrawler(HttpClient httpClient)
    {
        if (null == httpClient)
        {
            httpClient = new HttpClient();
        }

        this.httpClient = httpClient;
    }

    public HttpClient getHttpClient()
    {
        return httpClient;
    }

    public String getCookie()
    {
        return cookie;
    }

    public void setCookie(String cookie)
    {
        this.cookie = cookie;
    }

    public String getReferer()
    {
        return referer;
    }

    public void setReferer(String referer)
    {
        this.referer = referer;
    }

    @Override
    public T search(String url) throws Exception
    {
        if (null == url)
        {
            return null;
        }

        boolean useGzip = isGzipWebsite(url);

        String content = doGetRequest(url, useGzip);
        if (null == content)
        {
            throw new Exception(String.format("获取[%s]页面失败", url));
        }

        return parseResult(content);
    }

    public T search(String url, Map<String, String> params) throws Exception
    {
        if (null == url)
        {
            return null;
        }

        NameValuePair[] httpParam = null;
        if (null != params)
        {
            httpParam = createParams(params);
        }

        String content = doPostRequest(url, httpParam);

        if (null == content)
        {
            throw new Exception(String.format("获取[%s]页面失败", url));
        }

        return parseResult(content);
    }

    private NameValuePair[] createParams(Map<String, String> mapParam) throws Exception
    {
        NameValuePair[] params = new NameValuePair[mapParam.size()];

        int i = 0;
        for (Map.Entry<String, String> en : mapParam.entrySet())
        {
            NameValuePair p = new NameValuePair();
            p.setName(en.getKey());
            p.setValue(en.getValue());

            params[i] = p;
            i++;
        }

        return params;
    }

    private String doGetRequest(String url, boolean gzipWebsite) throws HttpException, IOException
    {
        GetMethod method = null;

        try
        {
            method = new GetMethod(url);
            setRequestHeaders(method);

            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK)
            {
                getLogger().error(
                        String.format("doGetRequest::fail for url %s,status is not ok!status is %d", url, statusCode));
                return null;
            }

            InputStream is = method.getResponseBodyAsStream();
            if (gzipWebsite)
            {
                is = new GZIPInputStream(method.getResponseBodyAsStream());
            }

            return method.getResponseBodyAsString();
        }
        finally
        {
            if (null != method)
            {
                method.releaseConnection();
            }
        }
    }
    
    /**
     * 收到响应状态为302后，重新发送请求
     * 
     * @param method
     * @return
     * @throws Exception
     */
    private String doPostRequest(PostMethod method) throws Exception
    {
        try
        {
            HttpClient httpClient = getHttpClient();
            httpClient.getState().clear();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
            httpClient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 300000);

            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK)
            {
                return null;
            }

            return method.getResponseBodyAsString();

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

    private String doPostRequest(String url, NameValuePair[] params) throws Exception
    {
        PostMethod method = null;
        try
        {
            HttpClient httpClient = getHttpClient();
            httpClient.getState().clear();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
            httpClient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 300000);
			Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", https);
            method = new PostMethod(url);

            // 设置请求头部信息
            setRequestHeaders(method);

            method.addParameters(params);

            int statusCode = httpClient.executeMethod(method);
            if(statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
            {
                method = processHeaders(method);
                return doPostRequest(method);
            }
            if (statusCode != HttpStatus.SC_OK)
            {
                return null;
            }

            return method.getResponseBodyAsString();

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
			Protocol.unregisterProtocol("https");
            if (null != method)
            {
                method.releaseConnection();
            }
        }
    }

    private boolean isGzipWebsite(String url) throws Exception
    {
        GetMethod method = null;

        try
        {
            method = new GetMethod(url);
            setRequestHeaders(method);

            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK)
            {
                getLogger().error(
                        String.format("isGzipWebsite::fail for url %s,status is not ok!status is %d", url, statusCode));
                return false;
            }

            Header resHeader = method.getResponseHeader("Content-Encoding");
            if (null != resHeader)
            {
                String contentEncoding = resHeader.getValue();
                if (StringUtils.isNotBlank(contentEncoding) && contentEncoding.toLowerCase().indexOf("gzip") > -1)
                {
                    return true;
                }
            }

            return false;
        }
        finally
        {
            if (null != method)
            {
                method.releaseConnection();
            }
        }
    }

    private void setRequestHeaders(HttpMethod method)
    {
        if (null != method)
        {
            method.addRequestHeader("Connection", "keep-alive");
            method.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            method.addRequestHeader("Pragma", "no-cache");
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            if (null != cookie)
            {
                method.addRequestHeader("Cookie", cookie);
                method.addRequestHeader("Referer", referer);
            }
        }
    }
    
    /**
     * 收到响应状态为302时重新处理请求头 默认不做任何改动
     * 
     * @param method
     * @return
     * @throws Exception
     */
    protected PostMethod processHeaders(PostMethod method) throws Exception
    {
        return method;
    }

    abstract protected T parseResult(String result) throws Exception;

    abstract protected Logger getLogger();
}
