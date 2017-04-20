package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/13.
 */
public class CallDurationModel
{
    @JSONField(name = "number_count")
    private Integer numberCount;// 联系人个数

    @JSONField(name = "call_out_count")
    private Integer callOutCount;// :主叫次数

    @JSONField(name = "call_in_count")
    private Integer callInCount;// :被叫次数

    @JSONField(name = "call_out_duration")
    private Integer callOutDuration;// :主叫时长

    @JSONField(serialize = false)
    private String callOutDurationStr;// :主叫时长

    @JSONField(name = "call_in_duration")
    private Integer callInDuration;// :被叫时长

    @JSONField(serialize = false)
    private String callInDurationStr;// 被叫时长

    @JSONField(name = "call_out_ratio")
    private String callOutRatio;// :主叫比率

    @JSONField(name = "call_in_ratio")
    private String callInRatio;// :被叫比率

    public Integer getNumberCount()
    {
        return numberCount;
    }

    public void setNumberCount(Integer numberCount)
    {
        this.numberCount = numberCount;
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

    public Integer getCallOutDuration()
    {
        return callOutDuration;
    }

    public void setCallOutDuration(Integer callOutDuration)
    {
        this.callOutDuration = callOutDuration;
    }

    public Integer getCallInDuration()
    {
        return callInDuration;
    }

    public void setCallInDuration(Integer callInDuration)
    {
        this.callInDuration = callInDuration;
    }

    public String getCallOutRatio()
    {
        return callOutRatio;
    }

    public void setCallOutRatio(String callOutRatio)
    {
        this.callOutRatio = callOutRatio;
    }

    public String getCallInRatio()
    {
        return callInRatio;
    }

    public void setCallInRatio(String callInRatio)
    {
        this.callInRatio = callInRatio;
    }

    public String getCallOutDurationStr()
    {
        return callOutDurationStr;
    }

    public void setCallOutDurationStr(String callOutDurationStr)
    {
        this.callOutDurationStr = callOutDurationStr;
    }

    public String getCallInDurationStr()
    {
        return callInDurationStr;
    }

    public void setCallInDurationStr(String callInDurationStr)
    {
        this.callInDurationStr = callInDurationStr;
    }
}