package com.credit.web.controller.open.blackbox;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.common.util.http.HttpUtil;
import com.credit.web.controller.AbstractBaseController;

/**
 * Created by wangjunling on 2017/3/15.
 */
@Controller
public class StructuredResultController extends AbstractBaseController
{
    @Value("${structured.url.prefix}")
    private String urlPrefix;

    @Value("${structured.url.suffix}")
    private String urlSuffix;

    @RequestMapping(value = "/service", method = RequestMethod.GET, params = "t=structured_result")
    @ResponseBody
    public ResultJson result(@RequestParam(required = false, name = "trade_no") String tradeNo)
    {
        if (StringUtils.isBlank(tradeNo))
        {
            return new ResultJson().paramError("交易编号为空");
        }
        String respResult;
        try
        {
            respResult = HttpUtil.getReq(urlPrefix + tradeNo + urlSuffix, null);
        }
        catch (Exception e)
        {
            logger.error("请求结果异常", e);
            return new ResultJson().error("请求结果异常");
        }
        ResultJson resultJson;
        try
        {
            resultJson = JSON.parseObject(respResult, ResultJson.class);
        }
        catch (Exception e)
        {
            logger.error("解析内容：{}，结果异常{}", respResult, e);
            return new ResultJson().error("解析返回结果异常");
        }
        return resultJson;
    }
}