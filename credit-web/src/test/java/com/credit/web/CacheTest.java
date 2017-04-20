package com.credit.web;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.credit.common.cache.AbstractCache;
import com.credit.common.cache.CacheFactory;
import com.credit.common.util.RandomUtil;

/**
 * Created by wangjunling on 2017/3/16.
 */
public class CacheTest
{
    private AbstractCache<Object, Object> cache;

    @Before
    public void before()
    {
        cache = CacheFactory.getInstance().newCache(100, TimeUnit.HOURS, 600000);

        for (int i = 0; i < 600000; i++)
        {
            try
            {
                cache.cache(RandomUtil.genRandomString(5), "xxx");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test()
    {
        long s = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
        {
            try
            {
                Object o = cache.get(RandomUtil.genRandomString(5));
                System.out.println(o);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println(e - s);
        System.out.println((e - s) / 1000);
    }
}
