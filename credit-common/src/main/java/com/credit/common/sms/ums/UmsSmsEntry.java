package com.credit.common.sms.ums;

import java.util.Date;
import java.util.Map;

import com.credit.common.sms.SmsEntry;

public class UmsSmsEntry extends SmsEntry
{
    public static final String PRIMARY_RESULT = "requestResult";// 发送短息返回的结果字符串

    public static final String RESULT = "result";// 发送结果

    public static final String TASK_ID = "task_id";// 任务ID

    public static final String RESULT_DESC = "description";// 结果描述

    public static final String FAIL_RECEIVER = "faillist";// 发送失败的人

    public static final String SMS_TASK_ID = "task_id";// taskId

    public static final Integer SMS_SEND_SUCCESS = Integer.valueOf(0);// 发送成功

    private String content;

    private String receiver;

    private Date scheduleTime;

    private String serialNumber;

    private String smsIds;

    private Map<String, String> resultMap;

    public Map<String, String> getResultMap()
    {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap)
    {
        this.resultMap = resultMap;
    }

    public String getSmsIds()
    {
        return smsIds;
    }

    public void setSmsIds(String smsIds)
    {
        this.smsIds = smsIds;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public Date getScheduleTime()
    {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime)
    {
        this.scheduleTime = scheduleTime;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public String getContent()
    {
        return content;
    }

    @Override
    public String getReceiver()
    {
        return receiver;
    }

}
