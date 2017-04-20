package com.credit.web.controller;

import java.util.Date;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/code",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson sendCode(String receiver)
    {
    	if(StringUtils.isBlank(receiver))
    	{
    		return new ResultJson().paramError("手机号不能为空");
		}
        String code = RandomUtil.genRandomNumberString(6);
        String content = registerTemplate.replace("#code#", code);
        Sms sms = new Sms();
        sms.setCreateTime(new Date());
        sms.setReceiver(receiver);
        sms.setSmsContent(content);
        sms.setSmsCode(code);
        try
        {
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
