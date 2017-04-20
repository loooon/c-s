package com.credit.web.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.credit.common.cache.CacheFactory;
import com.credit.common.cache.ICache;
import com.credit.common.util.servlet.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.common.result.ResultJson;
import com.credit.common.util.RandomUtil;
import com.credit.entity.Sms;
import com.credit.service.SmsService;

/**
 * Created by wangjunling on 2017/3/28.
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends AbstractBaseController
{
    @Resource
    private SmsService smsService;

    @Value("${sms.register.template}")
    private String registerTemplate;

    @Value("${sms.single.ip.max.times}")
    private Integer smsSingleIpMaxTimes;

    @Value("${sms.single.phone.max.times}")
    private Integer smsSinglePhoneMaxTimes;

    private static final ICache<String, Integer> singleIpSmsCache = CacheFactory.getInstance()
            .newCache(1, TimeUnit.DAYS, 100000);

    private static final ICache<String, Integer> singlePhoneSmsCache = CacheFactory.getInstance()
            .newCache(1, TimeUnit.DAYS, 100000);

    @RequestMapping(value = "/code",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson sendCode(String receiver, HttpServletRequest request)
    {
    	if(StringUtils.isBlank(receiver))
    	{
    		return new ResultJson().paramError("手机号不能为空");
		}
        String ip = RequestUtil.gettRequestIP(request);
        String code = RandomUtil.genRandomNumberString(6);
        String content = registerTemplate.replace("#code#", code);
        Sms sms = new Sms();
        sms.setCreateTime(new Date());
        sms.setReceiver(receiver);
        sms.setSmsContent(content);
        sms.setSmsCode(code);
        try
        {
            if(isFluentQuery(singleIpSmsCache,ip,smsSingleIpMaxTimes))
            {
                return new ResultJson().paramError("每个IP每天最多请求10次短信验证码");
            }
            if(isFluentQuery(singlePhoneSmsCache,receiver,smsSinglePhoneMaxTimes))
            {
                return new ResultJson().paramError("每个手机号每天最多请求5次短信验证码");
            }
            sms = smsService.saveSend(sms);
        }
        catch (Exception e)
        {
            logger.error("发送短信异常", e);
            return new ResultJson().error(ResultJson.STATUS_CODE_FAIL, "发送失败");
        }
        return new ResultJson().success(sms.getSmsId());
    }

}
