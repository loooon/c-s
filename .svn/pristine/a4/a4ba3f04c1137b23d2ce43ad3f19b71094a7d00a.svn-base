package com.credit.entity;

import javax.persistence.*;

/**
 * Created by Michael Chan on 3/24/2017.
 */
@Entity
@Table(name = "tb_dun_number_dist")
public class DunNumberDist
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_phone_province")
    private String userPhoneProvince;

    @Column(name = "call_from_province")
    private String callFromProvince;

    @Column(name = "from_province_number")
    private Integer fromProvinceNumber;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserPhoneProvince()
    {
        return userPhoneProvince;
    }

    public void setUserPhoneProvince(String userPhoneProvince)
    {
        this.userPhoneProvince = userPhoneProvince;
    }

    public String getCallFromProvince()
    {
        return callFromProvince;
    }

    public void setCallFromProvince(String callFromProvince)
    {
        this.callFromProvince = callFromProvince;
    }

    public Integer getFromProvinceNumber()
    {
        return fromProvinceNumber;
    }

    public void setFromProvinceNumber(Integer fromProvinceNumber)
    {
        this.fromProvinceNumber = fromProvinceNumber;
    }
}
