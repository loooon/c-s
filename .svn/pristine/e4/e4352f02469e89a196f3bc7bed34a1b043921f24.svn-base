package com.credit.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Michael Chan on 4/13/2017.
 */
@Entity
@Table(name = "tb_query_call_details")
public class QueryCallDetails
{
    /**联系人类型-固话*/
    public static final int CONTACT_TYPE_TEL = 0;

    /**联系人类型-手机*/
    public static final int CONTACT_TYPE_PHONE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cdid")
    private Integer cdid;

    @Column(name = "lid")
    private Integer lid;

    @Column(name = "call_time")
    @JSONField(name = "call_time")
    private String callTime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "call_model")
    @JSONField(name = "call_model")
    private String callModel;

    @Column(name = "format_call_model")
    @JSONField(name = "format_call_model")
    private String formatCallModel;

    @Column(name = "contact")
    private String contact;

    @Column(name = "contact_type")
    @JSONField(name = "contact_type")
    private Integer contactType;

    @Column(name = "call_addr")
    @JSONField(name = "call_addr")
    private String callAddr;

    @Column(name = "contact_addr")
    @JSONField(name = "contact_addr")
    private String contactAddr;

    @Column(name = "called_type")
    private String calledType;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "create_time")
    private Date createTime;

    public Integer getCdid()
    {
        return cdid;
    }

    public void setCdid(Integer cdid)
    {
        this.cdid = cdid;
    }

    public Integer getLid()
    {
        return lid;
    }

    public void setLid(Integer lid)
    {
        this.lid = lid;
    }

    public String getCallTime()
    {
        return callTime;
    }

    public void setCallTime(String callTime)
    {
        this.callTime = callTime;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public String getCallModel()
    {
        return callModel;
    }

    public void setCallModel(String callModel)
    {
        this.callModel = callModel;
    }

    public String getFormatCallModel()
    {
        return formatCallModel;
    }

    public void setFormatCallModel(String formatCallModel)
    {
        this.formatCallModel = formatCallModel;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public Integer getContactType()
    {
        return contactType;
    }

    public void setContactType(Integer contactType)
    {
        this.contactType = contactType;
    }

    public String getCallAddr()
    {
        return callAddr;
    }

    public void setCallAddr(String callAddr)
    {
        this.callAddr = callAddr;
    }

    public String getContactAddr()
    {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr)
    {
        this.contactAddr = contactAddr;
    }

    public String getCalledType()
    {
        return calledType;
    }

    public void setCalledType(String calledType)
    {
        this.calledType = calledType;
    }

    public Double getCost()
    {
        return cost;
    }

    public void setCost(Double cost)
    {
        this.cost = cost;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
