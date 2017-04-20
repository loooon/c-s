package com.credit.web.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/13.
 */
public class GrayscaleOrgStat
{
	@JSONField(name = "basic_info")
    private BasicInfo basicInfo = new BasicInfo();

    @JSONField(name = "attribute_tag")
    private AttributeTag attributeTag = new AttributeTag();

    @JSONField(name = "dun_tel_tag")
    private DunTelTag dunTelTag = new DunTelTag();

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

    public DunTelTag getDunTelTag()
    {
        return dunTelTag;
    }

    public void setDunTelTag(DunTelTag dunTelTag)
    {
        this.dunTelTag = dunTelTag;
    }
}
