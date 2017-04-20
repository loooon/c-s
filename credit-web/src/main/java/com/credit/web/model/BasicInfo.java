package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/10.
 */
public class BasicInfo
{
    private String uname;// uname:姓名

    private String idcard;// idcard:身份证号

    private String phone;// phone:手机号

    private Integer age;// age:年龄

    @JSONField(name = "effective_idcard")
    private String effectiveIdcard;// effective_idcard:有效/无效

    private String operator;// operator:运营商

    @JSONField(name = "phone_ownership")
    private String phoneOwnership;// phone_ownership:归属地

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
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

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getEffectiveIdcard()
    {
        return effectiveIdcard;
    }

    public void setEffectiveIdcard(String effectiveIdcard)
    {
        this.effectiveIdcard = effectiveIdcard;
    }

    public String getPhoneOwnership()
    {
        return phoneOwnership;
    }

    public void setPhoneOwnership(String phoneOwnership)
    {
        this.phoneOwnership = phoneOwnership;
    }
}