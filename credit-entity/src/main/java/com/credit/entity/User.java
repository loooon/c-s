package com.credit.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Created by wangjunling on 2017/3/7.
 */
@Entity
@Table(name = "tb_user")
public class User
{
    public static final int NOT_APPROVED = 0;      //审核未通过

    public static final int UNDER_REVIEW = 1;      //审核中

    public static final int APPROVED = 2;      //审核通过

    public static final int USER_ADMIN = 0;     // 管理员

    public static final int INDIVIDUAL = 1;     // 个人

    public static final int ENTERPRISE = 2;     // 企业

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "vkey")
    private String vKey;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "email")
    private String email;

    @Column(name = "verify_state_code")
    private Integer verifyStateCode;

    @Column(name = "verify_state_name")
    private String verifyStateName;

    @Column(name = "user_nickname")
    private String userNickName;

    @Column(name = "password")
    private String password;

    @Column(name = "reg_time")
    private Date regTime;

    @Column(name = "visits")
    private Integer visits;

    @Column(name = "last_logintime")
    private Date lastLoginTime;

    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sms_upload_count")
    private Integer smsUploadCount;

    @Column(name = "dun_number_mark_count")
    private Integer dunNumberMarkCount;

    @Column(name = "call_detail_mark_count")
    private Integer callDetailMarkCount;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getVerifyStateCode()
    {
        return verifyStateCode;
    }

    public void setVerifyStateCode(Integer verifyStateCode)
    {
        this.verifyStateCode = verifyStateCode;
    }

    public String getVerifyStateName()
    {
        return verifyStateName;
    }

    public void setVerifyStateName(String verifyStateName)
    {
        this.verifyStateName = verifyStateName;
    }

    public String getUserNo()
    {
        return userNo;
    }

    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    public String getvKey()
    {
        return vKey;
    }

    public void setvKey(String vKey)
    {
        this.vKey = vKey;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserNickName()
    {
        return userNickName;
    }

    public void setUserNickName(String userNickName)
    {
        this.userNickName = userNickName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getRegTime()
    {
        return regTime;
    }

    public void setRegTime(Date regTime)
    {
        this.regTime = regTime;
    }

    public Integer getVisits()
    {
        return visits;
    }

    public void setVisits(Integer visits)
    {
        this.visits = visits;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getUserType()
    {
        return userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Integer getSmsUploadCount()
    {
        return smsUploadCount;
    }

    public void setSmsUploadCount(Integer smsUploadCount)
    {
        this.smsUploadCount = smsUploadCount;
    }

    public Integer getDunNumberMarkCount()
    {
        return dunNumberMarkCount;
    }

    public void setDunNumberMarkCount(Integer dunNumberMarkCount)
    {
        this.dunNumberMarkCount = dunNumberMarkCount;
    }

    public Integer getCallDetailMarkCount()
    {
        return callDetailMarkCount;
    }

    public void setCallDetailMarkCount(Integer callDetailMarkCount)
    {
        this.callDetailMarkCount = callDetailMarkCount;
    }
}