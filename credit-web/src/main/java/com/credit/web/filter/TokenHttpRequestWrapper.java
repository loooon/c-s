package com.credit.web.filter;

import com.credit.web.util.HttpHelper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Michael Chan on 4/13/2017.
 */
public class TokenHttpRequestWrapper extends HttpServletRequestWrapper
{

    private String body;

    public TokenHttpRequestWrapper(HttpServletRequest request) {
        super(request);
        body = HttpHelper.getRequestBody(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public String getBody(){
        return body;
    }
}