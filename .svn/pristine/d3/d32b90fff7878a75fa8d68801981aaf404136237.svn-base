package com.credit.web.controller.open.blackbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.credit.common.result.ResultJson;
import com.credit.common.util.NumberFormat;
import com.credit.common.util.time.TimeUtil;
import com.credit.dto.GrayscaleVariateDto;
import com.credit.entity.Applicant;
import com.credit.service.UserGrayscaleStatVariableService;
import com.credit.web.controller.AbstractBaseController;
import com.credit.web.util.CallDetailUtil;
import com.credit.web.util.GrayscaleStatUtil;

/**
 * Created by wangjunling on 2017/4/12.
 */
@Controller
public class GrayscaleStatController extends AbstractBaseController
{
	@Resource
	private UserGrayscaleStatVariableService userGrayscaleStatVariableService;

	@RequestMapping(value = "/service", method = RequestMethod.POST, params = "t=grayscale_stat")
	@ResponseBody
	public ResultJson uploadSms(String phone, String name, String idcard, String imei, String imsi,
								@RequestParam(name = "apply_date", required = false) String applyDateStr,
								@RequestParam(name = "file_charset", defaultValue = "UTF-8") String fileCharset,
								HttpServletRequest request, String vkey)
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
		if (StringUtils.isNotBlank(applyDateStr))
		{
			try
			{
				Date applyDate = TimeUtil.parse(applyDateStr, "yyyy-MM-dd");
				if (applyDate == null)
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
			List<String> readCallDetailFileErrMsg = CallDetailUtil.readCallDetailFile(applicant, fileCharset, request);
			errMsgList.addAll(readCallDetailFileErrMsg);
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
				List<String> readCallDetailJsonArrayErrMsg = CallDetailUtil.readCallDetailJsonArray(applicant,
						callDetailStr);
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
		Date appleDate;
		if (applicant.getApplyDate() != null)
		{
			appleDate = applicant.getApplyDate();
		}
		else
		{
			appleDate = GrayscaleStatUtil.getApplyDate(applicant.getCallDetailsList());
		}
		if (appleDate == null)
		{
			return new ResultJson().paramError("详单中通话时间格式错误");
		}
		try
		{
			List<GrayscaleVariateDto> dtoList = userGrayscaleStatVariableService.searchDtoByVkey(vkey, appleDate);
			Map<String, Integer> resultMap = GrayscaleStatUtil.resolver(applicant.getCallDetailsList(), dtoList);
			return new ResultJson().success(resultMap);
		}
		catch (Exception e)
		{
			logger.error("统计详单异常", e);
			return new ResultJson().error();
		}
	}

}
