package com.credit.web.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.credit.entity.QueryContact;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.exception.ServiceException;
import com.credit.common.util.NumberFormat;
import com.credit.common.util.Validator;
import com.credit.common.util.time.TimeUtil;
import com.credit.entity.CallDetails;
import com.credit.entity.QueryCallDetails;
import com.credit.enumeration.CallModelEnum;
import com.credit.service.cache.PhoneSegmentInfoCache;
import com.google.common.collect.Lists;

/**
 * Created by Michael Chan on 4/13/2017.
 */
public class LogUtil
{
    public static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static final Pattern CALL_TIME_PATTERN = Pattern
            .compile("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}");

    private static final Pattern CALL_TIME_2_PATTERN = Pattern
            .compile("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}");

    private static final String CALL_DETAIL_SPLIT_REGEX = ",|\t";

    public static List<QueryCallDetails> readCallDetailJsonArray(String callDetailJsonArray, Integer lid)
            throws ServiceException
    {
        List<QueryCallDetails> queryCallDetailss = Lists.newArrayList();
        if (StringUtils.isEmpty(callDetailJsonArray))
        {
            return queryCallDetailss;
        }
        List<String> errMsgList = new ArrayList<String>();
        List<JSONObject> jsonObjectList;

        try
        {
            jsonObjectList = JSONArray.parseArray(callDetailJsonArray, JSONObject.class);
        }
        catch (Exception e)
        {
            logger.warn("记录日志转换JSON数组字符串为JSON数组对象失败:", e);
            throw new ServiceException("记录日志详单参数值格式有误【必须为JSON数组字符串】");
        }
        for (JSONObject jsonObject : jsonObjectList)
        {
            try
            {
                QueryCallDetails queryCallDetails = new QueryCallDetails();
                queryCallDetails.setLid(lid);
                queryCallDetails.setCallTime(formatCallTime(jsonObject.getString("call_time")));
                queryCallDetails.setDuration(formatDuration(jsonObject.getString("duration")));
                queryCallDetails.setCallModel(jsonObject.getString("call_model"));
                queryCallDetails.setFormatCallModel(formatCallModel(queryCallDetails.getCallModel()));
                queryCallDetails.setContact(formatContactNumber(jsonObject.getString("contact")));
                queryCallDetails.setContactType(NumberFormat.isPhoneNumber(queryCallDetails.getContact())
                        ? QueryCallDetails.CONTACT_TYPE_PHONE : QueryCallDetails.CONTACT_TYPE_TEL);
                queryCallDetails.setCallAddr(
                        formatContactAddr(queryCallDetails.getContact(), jsonObject.getString("call_addr")));
                queryCallDetails.setContactAddr(jsonObject.getString("contact_addr"));
                queryCallDetails.setCalledType(jsonObject.getString("called_type"));
                queryCallDetails.setCreateTime(new Date());
                queryCallDetailss.add(queryCallDetails);
                // applicant.getCallDetailsList().add(queryCallDetails);
            }
            catch (Exception e)
            {
                logger.error("记录日志解析详单记录[" + jsonObject + "]失败", e);
                errMsgList.add("记录日志解析详单记录[" + jsonObject + "]失败:" + e.getMessage());
            }
        }
        return queryCallDetailss;
    }

    /**
     * 格式化通话开始时间，目前支持yyyy-MM-dd HH:mm:ss和yyyy/MM/dd
     * HH:mm:ss，返回格式化后的结果:yyyy-MM-dd HH:mm:ss,若通话时间处理异常或为空，则返回null
     */
    private static final String formatCallTime(final String callTimeStr)
    {
        if (StringUtils.isEmpty(callTimeStr))
        {
            return "";
        }
        try
        {
            Matcher matcher = CALL_TIME_PATTERN.matcher(callTimeStr.trim());
            if (!matcher.matches())
            {
                matcher = CALL_TIME_2_PATTERN.matcher(callTimeStr.trim());
                if (matcher.matches())
                {
                    return TimeUtil.format(TimeUtil.parse(callTimeStr, "yyyy/MM/dd HH:mm:ss"), TimeUtil.FORMAT_NORMAL);
                }
                logger.debug("通话开始时间[" + callTimeStr + "]不正确");
                return "";
            }
            return callTimeStr.replaceAll("\\s{2,}", " ").trim();
        }
        catch (Exception e)
        {
            logger.error("校验通话开始时间[" + callTimeStr + "]失败:", e);
        }
        return "";
    }

    /**
     * 格式化通话时长，以秒为单位返回通话时长，当通话时长为空或处理失败时，返回的通话时长为0
     */
    private static final int formatDuration(final String durationStr)
    {
        if (StringUtils.isEmpty(durationStr))
        {
            return 0;
        }
        try
        {
            return Integer.valueOf(durationStr);
        }
        catch (Exception e)
        {
            logger.warn("时长[" + durationStr + "]转换为秒失败:", e);
        }
        return 0;
    }

    /**
     * 格式化呼叫模式
     */
    private static final String formatCallModel(final String callModel) throws Exception
    {
        if (StringUtils.isEmpty(callModel))
        {
            return "";
        }
        if (callModel.contains("未接") || callModel.contains("被") || callModel.contains("入"))
        {
            return CallModelEnum.CALL_IN.getComment();
        }
        else
        {
            return CallModelEnum.CALL_OUT.getComment();
        }
    }

    private static String formatContactAddr(String contactNumber, String contactAddr) throws Exception
    {
        if (StringUtils.isNotBlank(contactAddr))
        {
            return contactAddr;

        }
        if (StringUtils.isBlank(contactNumber) || !Validator.isTelphone(contactNumber))
        {
            return "";
        }
        String phoneOwnership = PhoneSegmentInfoCache.getInstance().getPhoneOwnership(contactNumber);
        if (phoneOwnership == null)
        {
            return "";
        }
        return phoneOwnership;
    }

    private static String formatContactNumber(String contactNumber) throws Exception
    {
        if (StringUtils.isEmpty(contactNumber))
        {
            throw new ServiceException("联系人号码不能为空");
        }
        String tagContactNumber = NumberFormat.formatNumber(contactNumber.trim());
        if (StringUtils.isEmpty(tagContactNumber))
        {
            throw new ServiceException("联系人号码[" + contactNumber.trim() + "]不正确");
        }
        return tagContactNumber;
    }

    public static List<QueryCallDetails> readFile(InputStream inputStream, String fileCharset,Integer lid) throws ServiceException
    {
        List<QueryCallDetails> queryCallDetailss = new ArrayList<>();
        try
        {
            if (inputStream != null)
            {
                List<String> stringList = IOUtils.readLines(inputStream, fileCharset);
                for (String line : stringList)
                {
                    try
                    {
                        String[] strings = line.split(CALL_DETAIL_SPLIT_REGEX, -1);
                        if (strings.length < 7)
                        {
                            throw new ServiceException("数据项格式不正确");
                        }
                        QueryCallDetails queryCallDetails = new QueryCallDetails();
                        queryCallDetails.setLid(lid);
                        queryCallDetails.setCallTime(formatCallTime(strings[0]));
                        queryCallDetails.setDuration(formatDuration(strings[1]));
                        queryCallDetails.setCallModel(strings[2]);
                        queryCallDetails.setFormatCallModel(formatCallModel(queryCallDetails.getCallModel()));
                        queryCallDetails.setContact(formatContactNumber(strings[3]));
                        queryCallDetails.setContactType(NumberFormat.isPhoneNumber(queryCallDetails.getContact())
                                ? CallDetails.CONTACT_TYPE_PHONE : CallDetails.CONTACT_TYPE_TEL);
                        queryCallDetails.setCallAddr(StringUtils.trimToNull(strings[4]));
                        queryCallDetails.setContactAddr(
                                formatContactAddr(queryCallDetails.getContact(), StringUtils.trimToNull(strings[5])));
                        queryCallDetails.setCalledType(StringUtils.trimToNull(strings[6]));
                        queryCallDetails.setCreateTime(new Date());
                        queryCallDetailss.add(queryCallDetails);
                    }
                    catch (Exception e)
                    {
                        logger.warn("记录日志解析详单行内容[" + line + "]失败", e);
                    }

                }
            }
        }
        catch (Exception e)
        {
            logger.error("记录日志读取文件失败", e);
            throw new ServiceException("记录日志读取详单文件异常");
        }
        return queryCallDetailss;
    }

    public static List<QueryContact> readQueryContactJsonArray(String contactsJson, Integer lid) throws ServiceException
    {
        List<QueryContact> queryContacts = Lists.newArrayList();
        if (StringUtils.isEmpty(contactsJson))
        {
            return queryContacts;
        }
        List<JSONObject> jsonObjectList;

        try
        {
            jsonObjectList = JSONArray.parseArray(contactsJson, JSONObject.class);
        }
        catch (Exception e)
        {
            logger.warn("记录日志转换JSON数组字符串为JSON数组对象失败:", e);
            throw new ServiceException("记录日志联系人格式有误【必须为JSON数组字符串】");
        }
        for (JSONObject jsonObject : jsonObjectList)
        {
            try
            {
                QueryContact queryContact = new QueryContact();
                queryContact.setLid(lid);
                queryContact.setCname(jsonObject.getString("contact_name"));
                queryContact.setCnumber(jsonObject.getString("contact_number"));
                queryContact.setRelation(jsonObject.getString("relation"));
                queryContacts.add(queryContact);
            }
            catch (Exception e)
            {
                logger.error("记录日志解析联系人[" + jsonObject + "]失败", e);
            }
        }
        return queryContacts;
    }
}
