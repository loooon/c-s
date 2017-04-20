package com.credit.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.common.result.ResultJson;
import com.credit.common.util.Validator;
import com.credit.entity.CompanyApply;
import com.credit.service.CompanyApplyService;

/**
 * Created by wangjunling on 2017/3/22.
 */
@Controller
public class CompanyApplyController
{
    @Resource
    private CompanyApplyService companyApplyService;

    @RequestMapping(value = "/dun/company", method = RequestMethod.GET)
    public String index()
    {
        return "dun/apply";
    }

    @RequestMapping(value = "/dun/m/company", method = RequestMethod.GET)
    public String mIndex()
    {
        return "dun/mobile/apply";
    }

    @RequestMapping(value = "/dun/company", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson index(String companyName, String phone, String duty, String contacts)
    {
        if (StringUtils.isBlank(companyName))
        {
            return new ResultJson().paramError("公司名不能为空");
        }
        if (StringUtils.isBlank(contacts))
        {
            return new ResultJson().paramError("联系人不能为空");
        }
        if (StringUtils.isBlank(duty))
        {
            return new ResultJson().paramError("职务不能为空");
        }
        if (StringUtils.isBlank(phone))
        {
            return new ResultJson().paramError("手机号不能为空");
        }
        if (!Validator.isMobile(phone) && !Validator.isPhone(phone))
        {
            return new ResultJson().paramError("号码格式有误");
        }
        CompanyApply apply = new CompanyApply();
        Date now = new Date();
        apply.setCompanyName(companyName);
        apply.setPhone(phone);
        apply.setContacts(contacts);
        apply.setDuty(duty);
        apply.setCreateDate(now);
        apply.setUpdateDate(now);
        companyApplyService.save(apply);
        return new ResultJson().success();
    }
}
