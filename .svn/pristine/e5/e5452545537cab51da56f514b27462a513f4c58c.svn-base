package com.credit.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Created by wangjunling on 2017/3/9.
 */
@Entity
@Table(name = "tb_applicant")
public class Applicant
{
    public static final String CALL_DETAIL_START_TIME_FORMAT_PATTERN = "yyyyMMdd";

    public static final String CALL_DETAIL_END_TIME_FORMAT_PATTERN = CALL_DETAIL_START_TIME_FORMAT_PATTERN;

    @Id
    @Column(name = "aid")
    private String aid;

    @Column(name = "aname")
    private String aname;

    @Column(name = "idcard")
    private String idcard;

    @Column(name = "phone")
    private String phone;

    @Column(name = "imei")
    private String imei;

    @Column(name = "imsi")
    private String imsi;

    @Column(name = "org_sn")
    private String orgSn;

    @Column(name = "apply_date")
    private Date applyDate;

    @Column(name = "query_time")
    private Date queryTime;

    @Column(name = "hit")
    private Boolean hit;

    // 详单集合
	@Transient
    private List<CallDetails> callDetailsList = new ArrayList<CallDetails>();



    public String getAid()
    {
        return aid;
    }

    public void setAid(String aid)
    {
        this.aid = aid;
    }

    public String getAname()
    {
        return aname;
    }

    public void setAname(String aname)
    {
        this.aname = aname;
    }

    public String getIdcard()
    {
        return idcard;
    }

    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getImei()
    {
        return imei;
    }

    public void setImei(String imei)
    {
        this.imei = imei;
    }

    public String getImsi()
    {
        return imsi;
    }

    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    public String getOrgSn()
    {
        return orgSn;
    }

    public void setOrgSn(String orgSn)
    {
        this.orgSn = orgSn;
    }

    public Date getQueryTime()
    {
        return queryTime;
    }

    public void setQueryTime(Date queryTime)
    {
        this.queryTime = queryTime;
    }

    public Date getApplyDate()
    {
        return applyDate;
    }

    public void setApplyDate(Date applyDate)
    {
        this.applyDate = applyDate;
    }

    public Boolean getHit()
    {
        return hit;
    }

    public void setHit(Boolean hit)
    {
        this.hit = hit;
    }



    public List<CallDetails> getCallDetailsList()
    {
        return callDetailsList;
    }



}
