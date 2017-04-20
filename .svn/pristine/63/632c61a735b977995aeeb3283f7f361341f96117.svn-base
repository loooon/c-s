package com.credit.common.context;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Created by wangjunling on 2017/3/17.
 */

public class PropertyFactory
{
    private static final Logger logger = LoggerFactory.getLogger(PropertyFactory.class);

    private static Properties properties;
    static
    {
        Resource resource = new ClassPathResource("conf/config.properties");
        try
        {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException e)
        {
            logger.error("获取spring配置文件信息失败!");
        }
    }

    public static String getProperty(String propertyName)
    {

        if (properties.containsKey(propertyName))
        {
            return properties.getProperty(propertyName);
        }
        return null;
    }

}
