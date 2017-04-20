package com.credit.web.controller;

import java.util.List;

import com.credit.service.cache.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.common.exception.ServiceException;
import com.credit.common.result.ResultJson;
import com.credit.entity.NumberTag;

/**
 * Created by Michael Chan on 3/9/2017.
 */
@Controller
@RequestMapping("/cache")
public class ClearCacheController extends AbstractBaseController
{
    @RequestMapping("/number/tag")
    @ResponseBody
    public ResultJson updateNumberTagsInfo()
    {
        try
        {
            NumberTagCache.getInstance().reloadAll();
            return  new ResultJson().success("更新号码标签成功！");
        } catch (ServiceException e)
        {
            logger.error("更新号码标签失败");
            return  new ResultJson().error(ResultJson.STATUS_CODE_FAIL,"更新号码标签失败！");
        }

    }

    @RequestMapping("/dun")
    @ResponseBody
    public ResultJson updateDunNumberDist()
    {
        try
        {
            DunNumberDistCache.getInstance().reloadAll();
            return  new ResultJson().success("更新催收号码成功！");
        } catch (ServiceException e)
        {
            logger.error("更新催收号码失败");
            return  new ResultJson().error(ResultJson.STATUS_CODE_FAIL,"更新催收号码失败！");
        }

    }

    @RequestMapping("/segment")
    @ResponseBody
    public ResultJson updatePhoneSegmentInfo()
    {
        try
        {
            PhoneSegmentInfoCache.getInstance().reloadAll();
            return  new ResultJson().success("更新号码信息成功！");
        } catch (ServiceException e)
        {
            logger.error("更新号码信息失败");
            return  new ResultJson().error(ResultJson.STATUS_CODE_FAIL,"更新号码信息失败！");
        }

    }

    @RequestMapping("/user/ip")
    @ResponseBody
    public ResultJson updateUserAuthIp()
    {
        try
        {
            UserAuthIpCache.getInstance().reloadAll();
            return  new ResultJson().success("更新用户授权ip成功！");
        } catch (ServiceException e)
        {
            logger.error("更新用户授权ip失败");
            return  new ResultJson().error(ResultJson.STATUS_CODE_FAIL,"更新用户授权ip失败！");
        }
    }

    @RequestMapping("/user/info")
    @ResponseBody
    public ResultJson updateUserInfo()
    {
        try
        {
            UserInfoCache.getInstance().reloadAll();
            return  new ResultJson().success("更新用户信息成功！");
        } catch (ServiceException e)
        {
            logger.error("更新用户信息失败");
            return  new ResultJson().error(ResultJson.STATUS_CODE_FAIL,"更新用户信息失败！");
        }
    }

    @RequestMapping("/all")
    @ResponseBody
    public ResultJson updateAllCache()
    {
        try
        {
            DunNumberDistCache.getInstance().reloadAll();
            NumberTagCache.getInstance().reloadAll();
            PhoneSegmentInfoCache.getInstance().reloadAll();
            UserAuthIpCache.getInstance().reloadAll();
            UserInfoCache.getInstance().reloadAll();
            return  new ResultJson().success("更新所有缓存成功！");
        } catch (ServiceException e)
        {
            logger.error("更新所有缓存失败");
            return  new ResultJson().error(ResultJson.STATUS_CODE_FAIL,"更新所有缓存失败！");
        }
    }
}
