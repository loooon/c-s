package com.credit.common.context;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class BeanFactory
{

    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    private static final ConcurrentHashMap<String, Object> beanCacheMap = new ConcurrentHashMap<String, Object>();

    private static WebApplicationContext applicationContext = null;
    static
    {
        try
        {
            applicationContext = ContextLoader.getCurrentWebApplicationContext();
        }
        catch (Exception e)
        {
            logger.error("获取spring上下文配置失败!");
        }
    }

    public static <T> T getBean(String beanName, Class<T> clz) throws Exception
    {
        if (beanCacheMap.containsKey(beanName))
        {
            return (T) beanCacheMap.get(beanName);
        }
        if (!applicationContext.containsBean(beanName))
        {
            logger.error("未发现bean对象{}", beanName);
            throw new Exception("未发现bean对象" + beanName);
        }

        T resultBean = (T) applicationContext.getBean(beanName);
        beanCacheMap.put(beanName, resultBean);
        return resultBean;
    }
}
