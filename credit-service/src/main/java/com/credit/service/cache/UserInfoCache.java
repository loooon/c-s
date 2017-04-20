package com.credit.service.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.spi.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit.common.cache.AbstractCache;
import com.credit.common.cache.CacheFactory;
import com.credit.common.context.BeanFactory;
import com.credit.common.exception.ServiceException;
import com.credit.entity.User;
import com.credit.service.UserService;
import com.credit.service.impl.UserServiceImpl;

/**
 * Created by Michael Chan on 3/16/2017.
 */
public class UserInfoCache
{
    private static final Logger logger = LoggerFactory.getLogger(UserInfoCache.class);

    private static final String USER_SERVICE = "userService";

    private UserService<User> userService;

    private AbstractCache<String, User> cache;

    public UserInfoCache()
    {
        try
        {
            userService = BeanFactory.getBean(USER_SERVICE, UserServiceImpl.class);
        }
        catch (Exception e)
        {
            userService = null;
            logger.error("初始化用户Service失败!", e);
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

	public void cache(User user) throws ServiceException {
		if(user == null)
		{
			return;
		}
		try {
			cache.cache(user.getvKey(),user);
		} catch (Exception e) {
			throw new ServiceException(ErrorCode.FLUSH_FAILURE, "更新用户信息失败", e);
		}
	}

	private static class UserInfoHolder
    {
        private static final UserInfoCache instance = new UserInfoCache();
    }

    public static UserInfoCache getInstance()
    {
        return UserInfoCache.UserInfoHolder.instance;
    }

    public void reloadAll() throws ServiceException
    {
        try
        {
            cache.invalidAllCache();
            List<User> userList = userService.searchAll();
            if(userList!=null)
            {
				for (User user : userList)
				{
                    if (user.getvKey() != null)
                    {
                        cache.cache(user.getvKey(), user);
                    }
				}
			}
        }
        catch (Exception e)
        {
            throw new ServiceException(ErrorCode.FLUSH_FAILURE, "重新加载用户信息失败", e);
        }
    }

    public User getUser(String vkey) throws ServiceException
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
            throw new ServiceException(ErrorCode.FLUSH_FAILURE, "从缓存中加载用户信息失败", e);
        }
    }
}