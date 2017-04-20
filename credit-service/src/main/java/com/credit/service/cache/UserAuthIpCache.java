package com.credit.service.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.spi.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit.common.cache.AbstractCache;
import com.credit.common.cache.CacheFactory;
import com.credit.common.context.BeanFactory;
import com.credit.common.exception.ServiceException;
import com.credit.entity.UserAuthIp;
import com.credit.service.UserAuthIpService;
import com.credit.service.impl.UserAuthIpServiceImpl;

/**
 * Created by Michael Chan on 3/16/2017.
 */
public class UserAuthIpCache
{
    private static final Logger logger = LoggerFactory.getLogger(UserInfoCache.class);

    private static final String USER_AUTH_SERVICE = "userAuthIpService";

    private UserAuthIpService<UserAuthIp> userAuthIpService;

    private AbstractCache<String, List<String>> cache;

    public UserAuthIpCache()
    {
        try
        {
            userAuthIpService = BeanFactory.getBean(USER_AUTH_SERVICE, UserAuthIpServiceImpl.class);
        }
        catch (Exception e)
        {
            userAuthIpService = null;
            logger.error("初始化userAuthIpService失败!", e);
        }
        try
        {
            cache = CacheFactory.getInstance().newCache(30, TimeUnit.DAYS, 1000);
        }
        catch (Exception e)
        {
            cache = null;
            logger.error("初始化用户信息失败!", e);
        }
    }

    private static class UserAuthIpHolder
    {
        private static final UserAuthIpCache instance = new UserAuthIpCache();
    }

    public static UserAuthIpCache getInstance()
    {
        return UserAuthIpCache.UserAuthIpHolder.instance;
    }

    public void reloadAll() throws ServiceException
    {

        if (null == userAuthIpService)
        {
            logger.error("初始化用户IP信息失败!");
            return;
        }

        try
        {
            List<UserAuthIp> userAuthIpList = userAuthIpService.searchAll();
            if (userAuthIpList != null)
            {
                for (UserAuthIp userAuthIp : userAuthIpList)
                {
					if (userAuthIp.getUserVkey() != null)
					{
						List<String> ipList = cache.get(userAuthIp.getUserVkey());
						if (ipList == null)
						{
							ipList = new ArrayList<>();
						}
						ipList.add(userAuthIp.getAuthorizedIp());
						cache.cache(userAuthIp.getUserVkey(), ipList);
					}

                }
            }
        }
        catch (Exception e)
        {
            throw new ServiceException(ErrorCode.FLUSH_FAILURE, "重新加载标签信息失败", e);
        }
    }

    public List<String> getIPList(String vkey) throws ServiceException
    {
        try
        {
            if (cache.size() == 0)
            {
                reloadAll();
            }
            return cache.get(vkey);
        }
        catch (Exception e)
        {
            throw new ServiceException(ErrorCode.FLUSH_FAILURE, "从缓存中加载用户ip信息失败", e);
        }
    }
}
