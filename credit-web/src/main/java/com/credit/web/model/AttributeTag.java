package com.credit.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/10.
 */
public class AttributeTag
{
    @JSONField(name = "friends_circle")
    private String friendsCircle;// :朋友圈

    private String active;// :活跃度

    private String stability;// :稳定度

    @JSONField(name = "contact_count")
    private Integer contactCount;// :联系人总数

    @JSONField(name = "periodic_contact_count")
    private Integer periodicContactCount;// :周期性联系人总数

    @JSONField(name = "month_avg_contact_count")
    private Integer monthAvgContactCount;// :月均联系人总数

    @JSONField(name = "month_avg_call_count")
    private Integer monthAvgCallCount;// :月均通话次数

    @JSONField(name = "avg_duration")
    private Integer avgDuration;// :平均通话时长

    @JSONField(serialize = false)
    private String avgDurationStr;// :平均通话时长

    @JSONField(name = "min_call_time_interval")
    private Integer minCallTimeInterval;// :最短通话间隔时间

    @JSONField(serialize = false)
    private String minCallTimeIntervalStr;// :最短通话间隔时间

    @JSONField(name = "max_call_time_interval")
    private Integer maxCallTimeInterval;// :最长通话间隔时间

    @JSONField(serialize = false)
    private String maxCallTimeIntervalStr;// :最长通话间隔时间

    @JSONField(name = "avg_call_time_interval")
    private Integer avgCallTimeInterval;// :平均通话间隔时间

    @JSONField(serialize = false)
    private String avgCallTimeIntervalStr;// :平均通话间隔时间

    @JSONField(name = "call_out_first_tier_cities_count")
    private int callOutFirstTierCitiesCount = 0;// :主叫一线城市联系人次数

    @JSONField(name = "max_call_time_slot")
    private String maxCallTimeSlot;// :通话频率最高的时间段(休息日，工作日)

    @JSONField(name = "grayscale_score")
    private Integer grayscaleScore;

    public String getFriendsCircle()
    {
        return friendsCircle;
    }

    public void setFriendsCircle(String friendsCircle)
    {
        this.friendsCircle = friendsCircle;
    }

    public String getActive()
    {
        return active;
    }

    public void setActive(String active)
    {
        this.active = active;
    }

    public String getStability()
    {
        return stability;
    }

    public void setStability(String stability)
    {
        this.stability = stability;
    }

    public Integer getContactCount()
    {
        return contactCount;
    }

    public void setContactCount(Integer contactCount)
    {
        this.contactCount = contactCount;
    }

    public Integer getPeriodicContactCount()
    {
        return periodicContactCount;
    }

    public void setPeriodicContactCount(Integer periodicContactCount)
    {
        this.periodicContactCount = periodicContactCount;
    }

    public Integer getMonthAvgContactCount()
    {
        return monthAvgContactCount;
    }

    public void setMonthAvgContactCount(Integer monthAvgContactCount)
    {
        this.monthAvgContactCount = monthAvgContactCount;
    }

    public Integer getMonthAvgCallCount()
    {
        return monthAvgCallCount;
    }

    public void setMonthAvgCallCount(Integer monthAvgCallCount)
    {
        this.monthAvgCallCount = monthAvgCallCount;
    }

    public Integer getAvgDuration()
    {
        return avgDuration;
    }

    public void setAvgDuration(Integer avgDuration)
    {
        this.avgDuration = avgDuration;
    }

    public Integer getMinCallTimeInterval()
    {
        return minCallTimeInterval;
    }

    public void setMinCallTimeInterval(Integer minCallTimeInterval)
    {
        this.minCallTimeInterval = minCallTimeInterval;
    }

    public Integer getMaxCallTimeInterval()
    {
        return maxCallTimeInterval;
    }

    public void setMaxCallTimeInterval(Integer maxCallTimeInterval)
    {
        this.maxCallTimeInterval = maxCallTimeInterval;
    }

    public Integer getAvgCallTimeInterval()
    {
        return avgCallTimeInterval;
    }

    public void setAvgCallTimeInterval(Integer avgCallTimeInterval)
    {
        this.avgCallTimeInterval = avgCallTimeInterval;
    }

    public int getCallOutFirstTierCitiesCount()
    {
        return callOutFirstTierCitiesCount;
    }

    public void setCallOutFirstTierCitiesCount(int callOutFirstTierCitiesCount)
    {
        this.callOutFirstTierCitiesCount = callOutFirstTierCitiesCount;
    }

    public String getMaxCallTimeSlot()
    {
        return maxCallTimeSlot;
    }

    public void setMaxCallTimeSlot(String maxCallTimeSlot)
    {
        this.maxCallTimeSlot = maxCallTimeSlot;
    }

    public Integer getGrayscaleScore()
    {
        return grayscaleScore;
    }

    public void setGrayscaleScore(Integer grayscaleScore)
    {
        this.grayscaleScore = grayscaleScore;
    }

    public String getAvgDurationStr()
    {
        return avgDurationStr;
    }

    public void setAvgDurationStr(String avgDurationStr)
    {
        this.avgDurationStr = avgDurationStr;
    }

    public String getMinCallTimeIntervalStr()
    {
        return minCallTimeIntervalStr;
    }

    public void setMinCallTimeIntervalStr(String minCallTimeIntervalStr)
    {
        this.minCallTimeIntervalStr = minCallTimeIntervalStr;
    }

    public String getMaxCallTimeIntervalStr()
    {
        return maxCallTimeIntervalStr;
    }

    public void setMaxCallTimeIntervalStr(String maxCallTimeIntervalStr)
    {
        this.maxCallTimeIntervalStr = maxCallTimeIntervalStr;
    }

    public String getAvgCallTimeIntervalStr()
    {
        return avgCallTimeIntervalStr;
    }

    public void setAvgCallTimeIntervalStr(String avgCallTimeIntervalStr)
    {
        this.avgCallTimeIntervalStr = avgCallTimeIntervalStr;
    }

    public static void main(String[] args)
    {
        AttributeTag attributeTagJson = new AttributeTag();
        attributeTagJson.setFriendsCircle("朋友圈");
        attributeTagJson.setActive("一般");
        attributeTagJson.setCallOutFirstTierCitiesCount(222);
        System.out.println(JSON.toJSONString(attributeTagJson));
    }
}
