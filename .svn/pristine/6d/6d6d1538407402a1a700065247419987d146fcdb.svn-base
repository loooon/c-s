package com.credit.web.controller.open.blackbox;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.credit.web.model.GrayscaleOrgStat;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.credit.common.result.ResultJson;
import com.credit.common.util.NumberFormat;
import com.credit.common.util.RandomUtil;
import com.credit.common.util.pdf.PdfUtil;
import com.credit.common.util.servlet.ResponseUtil;
import com.credit.common.util.time.TimeUtil;
import com.credit.entity.Applicant;
import com.credit.web.controller.AbstractBaseController;
import com.credit.web.model.GrayscaleModel;
import com.credit.web.util.CallDetailUtil;
import com.credit.web.util.ContactCheckUtil;
import com.credit.web.util.GrayscaleUtil;
import com.credit.web.util.VelocityEngineUtil;

/**
 * Created by wangjunling on 2017/3/14.
 */
@Controller
public class GrayscaleOrgStatController extends AbstractBaseController
{
    @RequestMapping(value = "/service", method = RequestMethod.POST, params = "t=grayscale_org_stat")
    @ResponseBody
    public ResultJson service(String phone, String name, String idcard, String imei, String imsi,
                          @RequestParam(name = "apply_date", required = false) String applyDateStr,
                          @RequestParam(name = "file_charset", defaultValue = "UTF-8") String fileCharset,
                          @RequestParam(name = "contacts", required = false) String contactsJson, HttpServletRequest request)
    {
        if (StringUtils.isBlank(phone))
        {
            return new ResultJson().paramError("手机号码为空");
        }
        Applicant applicant = new Applicant();
        try
        {
            String tagPhoneNumber = NumberFormat.formatNumber(phone);
            if (!NumberFormat.isPhoneNumber(tagPhoneNumber))
            {
                logger.debug("输入申请人电话号码:" + phone + ",转换后的号码" + tagPhoneNumber);
                return new ResultJson().paramError("申请人电话号码[" + phone + "]不正确");
            }
            applicant.setPhone(tagPhoneNumber);
        }
        catch (Exception e)
        {
            logger.warn("申请人电话号码[" + applicant.getPhone() + "]不正确:", e);
            return new ResultJson().paramError("申请人电话号码[" + applicant.getPhone() + "]不正确");
        }
        if(StringUtils.isNotBlank(applyDateStr))
        {
            try
            {
                Date applyDate = TimeUtil.parse(applyDateStr, "yyyy-MM-dd");
                if(applyDate==null)
                {
                    return new ResultJson().paramError("申请时间[" + applyDateStr + "]格式不正确");
                }
                applicant.setApplyDate(applyDate);
            }
            catch (Exception e)
            {
                logger.warn("申请时间[" + applyDateStr + "]格式不正确");
                return new ResultJson().paramError("申请时间[" + applyDateStr + "]格式不正确");
            }
        }

        applicant.setIdcard(idcard);
        applicant.setAname(name);
        applicant.setImei(imei);
        applicant.setImsi(imsi);
        applicant.setQueryTime(new Date());
        List<String> errMsgList = new ArrayList<>();
        try
        {
            MultipartFile multipartFile = CallDetailUtil.getMultipartFile(request,fileCharset);
            if (multipartFile != null)
            {
                errMsgList = CallDetailUtil.readFile(applicant, multipartFile.getInputStream(), fileCharset);
            }
        }
        catch (Exception e)
        {
            logger.error("读取详单文件异常", e);
            return new ResultJson().paramError(e.getMessage());
        }
        String callDetailStr = request.getParameter("call_details");
        if (CollectionUtils.isEmpty(applicant.getCallDetailsList()) && StringUtils.isBlank(callDetailStr))
        {
            if (CollectionUtils.isNotEmpty(errMsgList))
            {
                return new ResultJson().paramError(JSONArray.toJSONString(errMsgList));
            }
            return new ResultJson().paramError("详单为空");
        }
        try
        {
            if (StringUtils.isNotBlank(callDetailStr))
            {
                List<String> readCallDetailJsonArrayErrMsg = CallDetailUtil.readCallDetailJsonArray(applicant, callDetailStr);
                errMsgList.addAll(readCallDetailJsonArrayErrMsg);
            }
        }
        catch (Exception e)
        {
            logger.error("详单Json字符串转化异常", e);
            return new ResultJson().paramError("详单参数值格式有误【必须为JSON数组字符串】");
        }
        if (CollectionUtils.isEmpty(applicant.getCallDetailsList()))
        {
            if (CollectionUtils.isEmpty(errMsgList))
            {
                return new ResultJson().paramError("申请人详单内容为空");
            }
            return new ResultJson().paramError(JSONArray.toJSONString(errMsgList));
        }

        GrayscaleOrgStat grayscaleOrgStat = new GrayscaleOrgStat();
        try
        {
            GrayscaleUtil.buildGrayscaleOrgStat(grayscaleOrgStat, applicant);
        }
        catch (Exception e)
        {
            logger.error("详单统计错误", e);
            return new ResultJson().error("详单统计错误");
        }
        return new ResultJson().success(grayscaleOrgStat);

    }
}