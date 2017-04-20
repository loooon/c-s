package com.credit.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tb_sms")
public class Sms
{
    public static final Integer SMS_STATE_PENDING = 0; // 待处理

    public static final Integer SMS_STATE_SUCCESSED = 1; // 发送成功

    public static final Integer SMS_STATE_FAILED = 2; // 发送失败

    public static final Integer SMS_STATE_DELETED = 3; // 删除

    public static final String SMS_STATE_PENDING_NAME = "待处理";

    public static final String SMS_STATE_SUCCESSED_NAME = "发送成功";

    public static final String SMS_STATE_FAILED_NAME = "发送失败";

    public static final String SMS_STATE_DELETED_NAME = "删除";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sms_id")
    private Integer smsId;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "sms_serial_no")
    private String smsSerialNo;

    @Column(name = "sms_code")
    private String smsCode;

    @Column(name = "sms_content")
    private String smsContent;

    @Column(name = "sms_state")
    private Integer smsState;

    @Column(name = "sms_fail_reason")
    private String smsFailReason;

    @Column(name = "primary_result")
    private String primaryResult;

    @Column(name = "expire_time")
    private Date expireDate;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "lastupdatetime")
    private Date lastupdatetime;

    public Integer getSmsId()
    {
        return smsId;
    }

    public void setSmsId(Integer smsId)
    {
        this.smsId = smsId;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getSmsSerialNo()
    {
        return smsSerialNo;
    }

    public void setSmsSerialNo(String smsSerialNo)
    {
        this.smsSerialNo = smsSerialNo;
    }

    public String getSmsCode()
    {
        return smsCode;
    }

    public void setSmsCode(String smsCode)
    {
        this.smsCode = smsCode;
    }

    public String getSmsContent()
    {
        return smsContent;
    }

    public void setSmsContent(String smsContent)
    {
        this.smsContent = smsContent;
    }

    public Integer getSmsState()
    {
        return smsState;
    }

    public void setSmsState(Integer smsState)
    {
        this.smsState = smsState;
    }

    public String getSmsFailReason()
    {
        return smsFailReason;
    }

    public void setSmsFailReason(String smsFailReason)
    {
        this.smsFailReason = smsFailReason;
    }

    public String getPrimaryResult()
    {
        return primaryResult;
    }

    public void setPrimaryResult(String primaryResult)
    {
        this.primaryResult = primaryResult;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastupdatetime()
    {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime)
    {
        this.lastupdatetime = lastupdatetime;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }
}
