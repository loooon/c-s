package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Michael Chan on 3/13/2017.
 */
public class TopCallOutStat
{
    private String number;// 联系人号码【号码】

    @JSONField(name = "call_out_count")
    private Integer callOutCount;// 主叫次数

    @JSONField(name = "call_out_duration")
    private Integer callOutDuration;// 主叫总时长

    @JSONField(serialize = false)
    private String callOutDurationStr;// 主叫总时长

    @JSONField(name = "call_out_rate")
    private String callOutRate; // 主叫占比

    @JSONField(name = "periodically_contact")
    private String periodicallyContact;

    @JSONField(name = "call_frequency")
    private String callFrequency;

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Integer getCallOutCount()
    {
        return callOutCount;
    }

    public void setCallOutCount(Integer callOutCount)
    {
        this.callOutCount = callOutCount;
    }

    public Integer getCallOutDuration()
    {
        return callOutDuration;
    }

    public void setCallOutDuration(Integer callOutDuration)
    {
        this.callOutDuration = callOutDuration;
    }

    public String getCallOutRate()
    {
        return callOutRate;
    }

    public void setCallOutRate(String callOutRate)
    {
        this.callOutRate = callOutRate;
    }

    public String getPeriodicallyContact()
    {
        return periodicallyContact;
    }

    public void setPeriodicallyContact(String periodicallyContact)
    {
        this.periodicallyContact = periodicallyContact;
    }

    public String getCallFrequency()
    {
        return callFrequency;
    }

    public void setCallFrequency(String callFrequency)
    {
        this.callFrequency = callFrequency;
    }

    public String getCallOutDurationStr()
    {
        return callOutDurationStr;
    }

    public void setCallOutDurationStr(String callOutDurationStr)
    {
        this.callOutDurationStr = callOutDurationStr;
    }
}
