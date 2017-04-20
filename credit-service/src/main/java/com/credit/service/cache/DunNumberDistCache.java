package com.credit.service.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.credit.common.cache.CacheFactory;
import com.credit.common.exception.ServiceException;
import com.credit.common.util.security.DigestUtils;
import com.credit.entity.NumberTag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit.common.cache.ICache;
import com.credit.common.context.BeanFactory;
import com.credit.entity.DunNumberDist;
import com.credit.service.DunNumberDistService;
import com.credit.service.impl.DunNumberDistServiceImpl;

/**
 * Created by Michael Chan on 3/24/2017.
 */
public class DunNumberDistCache
{
    private static final Logger logger = LoggerFactory.getLogger(DunNumberDistCache.class);

    private static final String DUN_NUMBER_DIST_SERVICE_NAME = "dunNumberDistService";

    private DunNumberDistService<DunNumberDist> dunNumberDistService;

    private ICache<Integer, DunNumberDist> cache;

    private DunNumberDistCache()
    {
        try
        {
            dunNumberDistService =  BeanFactory.getBean(DUN_NUMBER_DIST_SERVICE_NAME, DunNumberDistServiceImpl.class);

        }
        catch (Exception e)
        {
            dunNumberDistService=null;
            logger.error("初始化催收号码分布图数据失败!", e);
        }

        try
        {
            cache = CacheFactory.getInstance().newCache(30, TimeUnit.DAYS, 10000000);
        }
        catch (Exception e)
        {
            cache = null;
            logger.error("初始化催收号码分布图数据失败!", e);
        }
    }

    public  static  class DunNumberDistHolder
    {
        private static final DunNumberDistCache instance = new DunNumberDistCache();
    }

    public static DunNumberDistCache getInstance()
    {
        return DunNumberDistHolder.instance;
    }


    public void reloadAll() throws ServiceException
    {

        if (null == dunNumberDistService)
        {
            logger.error("初始化催收号码分布图数据失败!");
            return;
        }

        List<DunNumberDist> dunNumberDists = dunNumberDistService.searchAll();

        reloadAll(dunNumberDists);
    }

    public void reloadAll(List<DunNumberDist> dunNumberDists) throws ServiceException
    {
        try
        {
            cache.invalidAllCache();
            if (null != dunNumberDists)
            {
                logger.info("共查询到" + dunNumberDists.size() + "条催收号码分布图数据");
                for (DunNumberDist dunNumberDist : dunNumberDists)
                {
                    cache.cache(dunNumberDist.getId(), dunNumberDist);
                }
                logger.info("共缓存" + cache.size() + "条数据");
            }
            else
            {
                logger.info("未查询到催收号码分布图数据");
            }
        }
        catch (Exception e)
        {
            throw new ServiceException(ErrorCode.FLUSH_FAILURE, "重新加载催收号码分布图数据失败", e);
        }
    }

    public List<DunNumberDist> getDunNumberDist(List<Integer> ids) throws ServiceException
    {
        if (CollectionUtils.isEmpty(ids))
        {
            return null;
        }
        try
        {
            if (cache.size() == 0)
            {
                reloadAll();
            }
            List<DunNumberDist> dunNumberDists = new ArrayList<>();
            for (Integer id : ids)
            {
                DunNumberDist dunNumberDist =cache.get(id);
                dunNumberDists.add(dunNumberDist);
            }
            return dunNumberDists;
        }
        catch (Exception e)
        {
            throw new ServiceException(ErrorCode.FLUSH_FAILURE, "从缓存中获取催收号码分布图数据失败", e);
        }
    }

}
