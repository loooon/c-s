package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/13.
 */
public class RiskStat
{
    // 联系人号码【号码】
    private String number;

    // 归属地
    private String ownership;

    // 机构类型【行业类型】
    @JSONField(name = "org_type")
    private String orgType;

    // 需求类型
    @JSONField(name = "demand_type")
    private String demandType;

    @JSONField(serialize = false)
    private String demandClass;

    @JSONField(serialize = false)
    private String orgClass;

    // 通话总时长
    @JSONField(name = "call_duration")
    private Integer callDuration;

    @JSONField(serialize = false)
    private String callDurationStr;

    // 联系总次数【联系次数】
    @JSONField(name = "call_count")
    private Integer callCount;

    // 主叫次数
    @JSONField(name = "call_out_count")
    private Integer callOutCount;

    // 被叫次数
    @JSONField(name = "call_in_count")
    private Integer callInCount;

    // 周期性联系【是否存在周期性联系】
    @JSONField(name = "periodically_contact")
    private String periodicallyContact;

    // 联系频率【互动标识】
    @JSONField(name = "call_frequency")
    private String callFrequency;

    @JSONField(name = "last_mark_date")
    private String lastMarkDate;

    @JSONField(serialize = false)
    private Double callFrequencyNum;

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getOwnership()
    {
        return ownership;
    }

    public void setOwnership(String ownership)
    {
        this.ownership = ownership;
    }

    public String getOrgType()
    {
        return orgType;
    }

    public void setOrgType(String orgType)
    {
        this.orgType = orgType;
    }

    public String getDemandType()
    {
        return demandType;
    }

    public void setDemandType(String demandType)
    {
        this.demandType = demandType;
    }

    public Integer getCallDuration()
    {
        return callDuration;
    }

    public void setCallDuration(Integer callDuration)
    {
        this.callDuration = callDuration;
    }

    public Integer getCallCount()
    {
        return callCount;
    }

    public void setCallCount(Integer callCount)
    {
        this.callCount = callCount;
    }

    public Integer getCallOutCount()
    {
        return callOutCount;
    }

    public void setCallOutCount(Integer callOutCount)
    {
        this.callOutCount = callOutCount;
    }

    public Integer getCallInCount()
    {
        return callInCount;
    }

    public void setCallInCount(Integer callInCount)
    {
        this.callInCount = callInCount;
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

    public String getLastMarkDate()
    {
        return lastMarkDate;
    }

    public void setLastMarkDate(String lastMarkDate)
    {
        this.lastMarkDate = lastMarkDate;
    }

    public Double getCallFrequencyNum()
    {
        return callFrequencyNum;
    }

    public void setCallFrequencyNum(Double callFrequencyNum)
    {
        this.callFrequencyNum = callFrequencyNum;
    }

    public String getCallDurationStr()
    {
        return callDurationStr;
    }

    public void setCallDurationStr(String callDurationStr)
    {
        this.callDurationStr = callDurationStr;
    }

    public String getDemandClass()
    {
        return demandClass;
    }

    public void setDemandClass(String demandClass)
    {
        this.demandClass = demandClass;
    }

    public String getOrgClass()
    {
        return orgClass;
    }

    public void setOrgClass(String orgClass)
    {
        this.orgClass = orgClass;
    }
}