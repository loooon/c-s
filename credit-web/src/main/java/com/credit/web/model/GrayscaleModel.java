package com.credit.web.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/13.
 */
public class GrayscaleModel
{
	@JSONField(name = "basic_info")
    private BasicInfo basicInfo = new BasicInfo();

    @JSONField(name = "attribute_tag")
    private AttributeTag attributeTag = new AttributeTag();

    @JSONField(name = "top_call_out_stat")
    private List<TopCallOutStat> topCallOutStat = new ArrayList<>();

    @JSONField(name = "top_call_in_stat")
    private List<TopCallInStat> topCallInStat = new ArrayList<>();

    @JSONField(name = "contact_area_stat")
    private List<ContactAreaStat> contactAreaStat = new ArrayList<>();

    @JSONField(name = "risk_stat")
    private List<RiskStat> riskStat = new ArrayList<>();

    @JSONField(name = "call_duration_stat")
    private CallDurationStat callDurationStat = new CallDurationStat();

    @JSONField(name = "contact_check")
    private List<ContactCheck> contactCheck = new ArrayList<>();

    public BasicInfo getBasicInfo()
    {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo)
    {
        this.basicInfo = basicInfo;
    }

    public AttributeTag getAttributeTag()
    {
        return attributeTag;
    }

    public void setAttributeTag(AttributeTag attributeTag)
    {
        this.attributeTag = attributeTag;
    }

    public List<TopCallOutStat> getTopCallOutStat()
    {
        return topCallOutStat;
    }

    public void setTopCallOutStat(List<TopCallOutStat> topCallOutStat)
    {
        this.topCallOutStat = topCallOutStat;
    }

    public List<TopCallInStat> getTopCallInStat()
    {
        return topCallInStat;
    }

    public void setTopCallInStat(List<TopCallInStat> topCallInStat)
    {
        this.topCallInStat = topCallInStat;
    }

    public List<ContactAreaStat> getContactAreaStat()
    {
        return contactAreaStat;
    }

    public void setContactAreaStat(List<ContactAreaStat> contactAreaStat)
    {
        this.contactAreaStat = contactAreaStat;
    }

    public List<RiskStat> getRiskStat()
    {
        return riskStat;
    }

    public void setRiskStat(List<RiskStat> riskStat)
    {
        this.riskStat = riskStat;
    }

    public CallDurationStat getCallDurationStat()
    {
        return callDurationStat;
    }

    public void setCallDurationStat(CallDurationStat callDurationStat)
    {
        this.callDurationStat = callDurationStat;
    }

    public List<ContactCheck> getContactCheck()
    {
        return contactCheck;
    }

    public void setContactCheck(List<ContactCheck> contactCheck)
    {
        this.contactCheck = contactCheck;
    }
}
