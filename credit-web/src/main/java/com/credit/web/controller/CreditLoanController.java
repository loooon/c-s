package com.credit.web.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.common.result.ResultJson;
import com.credit.common.util.Validator;
import com.credit.web.biz.CreditLoanService;

/**
 * Created by wangjunling on 2017/3/29.
 */
@Controller
public class CreditLoanController extends AbstractBaseController
{

    @RequestMapping("/user/credit/loan")
    public String search()
    {
        return "/user/credit_loan";
    }

    @RequestMapping(value = "/user/credit/loan", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson search(String phone, String name, String idcard, String imsi, String imei, String[] types)
    {
        ResultJson resultJson = new ResultJson();
        if (!checkParam(phone, name, idcard, types, resultJson))
        {
            return resultJson;
        }
        try
        {
            Map<String, Object> result = CreditLoanService.getInstance().search(phone, idcard, name, imsi, imei, types);
            if (result.size() == 0)
            {
                resultJson.setResult(ResultJson.STATUS_CODE_SUCCESS);
                resultJson.setMessage("暂无数据");
                return resultJson;
            }
            resultJson.setResult(ResultJson.STATUS_CODE_SUCCESS_YES_DATA);
            resultJson.setData(result);
        }
        catch (Exception e)
        {
            logger.error("加载信贷信息异常", e);
            resultJson.setResult(ResultJson.STATUS_CODE_EXCEPTION);
        }
        return resultJson;
    }

    private boolean checkParam(String phone, String name, String idcard, String[] types, ResultJson resultJson)
    {
        if (StringUtils.isBlank(phone))
        {
            resultJson.setResult(ResultJson.STATUS_CODE_PARAM_ERROR);
            resultJson.setMessage("手机号不能为空");
            return false;
        }
        if (!Validator.isMobile(phone))
        {
            resultJson.setResult(ResultJson.STATUS_CODE_PARAM_ERROR);
            resultJson.setMessage("手机号格式不正确");
            return false;
        }
        if (StringUtils.isBlank(name))
        {
            resultJson.setResult(ResultJson.STATUS_CODE_PARAM_ERROR);
            resultJson.setMessage("姓名不能为空");
            return false;
        }
        if (StringUtils.isBlank(idcard))
        {
            resultJson.setResult(ResultJson.STATUS_CODE_PARAM_ERROR);
            resultJson.setMessage("身份证号不能为空");
            return false;
        }
        if (types == null || types.length == 0)
        {
            resultJson.setResult(ResultJson.STATUS_CODE_PARAM_ERROR);
            resultJson.setMessage("请选择查询类型");
            return false;
        }
        return true;
    }
}
