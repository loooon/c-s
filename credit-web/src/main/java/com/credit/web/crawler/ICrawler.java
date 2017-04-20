package com.credit.web.crawler;

import java.util.Map;

public interface ICrawler<T>
{
    T search(String url) throws Exception;

    T search(String url, Map<String, String> params) throws Exception;
}
