package com.credit.web.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;

/**
 * Created by wangjunling on 2017/4/5.
 */
public class VelocityEngineUtil
{
    private static Map<String, Object> proMap = null;
    static
    {
        proMap = new HashMap<>();
        proMap.put("resource.loader", "class");
        proMap.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    public static VelocityEngine getInstance()
    {
        return Singleton.INSTANCE.getVelocityEngine();
    }

    public enum Singleton
    {
        INSTANCE;

        public VelocityEngine velocityEngine;

        Singleton()
        {
            velocityEngine = new VelocityEngine();
            for (Map.Entry<String, Object> entry : proMap.entrySet())
            {
                velocityEngine.setProperty(entry.getKey(), entry.getValue());
            }
        }

        public VelocityEngine getVelocityEngine()
        {
            return velocityEngine;
        }
    }
}
