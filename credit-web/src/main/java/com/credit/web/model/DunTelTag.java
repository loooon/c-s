package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

public class DunTelTag
{
    @JSONField(name = "dun_tel_count")
    private Integer dunTelCount;    //催收号码个数

    @JSONField(name = "dun_tel_like_count")
    private Integer dunTelLikeCount;    //疑似催收号码个数

    @JSONField(name = "other_dun_tel_count")
    private Integer otherDunTelCount;    //涉及委外催收的催收号码个数

    @JSONField(name = "other_dun_tel_orgs")
    private String otherDunTelOrgs;    //涉及委外催收的机构

    @JSONField(name = "bank_dun_tel_count")
    private Integer bankDunTelCount;    //涉及银行的催收号码个数

    @JSONField(name = "bank_dun_tel_orgs")
    private String bankDunTelOrgs;    //涉及银行的机构

    @JSONField(name = "if_dun_tel_count")
    private Integer iFDunTelCount;    //涉及互金构的催收号码个数

    @JSONField(name = "if_dun_tel_orgs")
    private String iFDunTelOrgs;    //涉及互金的机构

    public Integer getDunTelCount()
    {
        return dunTelCount;
    }

    public void setDunTelCount(Integer dunTelCount)
    {
        this.dunTelCount = dunTelCount;
    }

    public Integer getDunTelLikeCount()
    {
        return dunTelLikeCount;
    }

    public void setDunTelLikeCount(Integer dunTelLikeCount)
    {
        this.dunTelLikeCount = dunTelLikeCount;
    }

    public Integer getOtherDunTelCount()
    {
        return otherDunTelCount;
    }

    public void setOtherDunTelCount(Integer otherDunTelCount)
    {
        this.otherDunTelCount = otherDunTelCount;
    }

    public String getOtherDunTelOrgs()
    {
        return otherDunTelOrgs;
    }

    public void setOtherDunTelOrgs(String otherDunTelOrgs)
    {
        this.otherDunTelOrgs = otherDunTelOrgs;
    }

    public Integer getBankDunTelCount()
    {
        return bankDunTelCount;
    }

    public void setBankDunTelCount(Integer bankDunTelCount)
    {
        this.bankDunTelCount = bankDunTelCount;
    }

    public String getBankDunTelOrgs()
    {
        return bankDunTelOrgs;
    }

    public void setBankDunTelOrgs(String bankDunTelOrgs)
    {
        this.bankDunTelOrgs = bankDunTelOrgs;
    }

    public Integer getiFDunTelCount()
    {
        return iFDunTelCount;
    }

    public void setiFDunTelCount(Integer iFDunTelCount)
    {
        this.iFDunTelCount = iFDunTelCount;
    }

    public String getiFDunTelOrgs()
    {
        return iFDunTelOrgs;
    }

    public void setiFDunTelOrgs(String iFDunTelOrgs)
    {
        this.iFDunTelOrgs = iFDunTelOrgs;
    }
}