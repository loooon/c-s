package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/13.
 */
public class CallDurationStat
{
    @JSONField(name = "working_day")
    // 工作日
    private CallDurationModel workingDay;

    // 周末
    private CallDurationModel weekend;

    public CallDurationModel getWorkingDay()
    {
        return workingDay;
    }

    public void setWorkingDay(CallDurationModel workingDay)
    {
        this.workingDay = workingDay;
    }

    public CallDurationModel getWeekend()
    {
        return weekend;
    }

    public void setWeekend(CallDurationModel weekend)
    {
        this.weekend = weekend;
    }
}