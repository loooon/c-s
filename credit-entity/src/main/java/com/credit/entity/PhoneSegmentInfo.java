package com.credit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wangjunling on 2017/3/14.
 */
@Entity
@Table(name = "tb_phone_segment_info")
public class PhoneSegmentInfo
{
	@Id
    @Column(name = "phone_segment")
    private String phoneSegment;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "operator")
    private String operator;

    public String getPhoneSegment()
    {
        return phoneSegment;
    }

    public void setPhoneSegment(String phoneSegment)
    {
        this.phoneSegment = phoneSegment;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }
}
