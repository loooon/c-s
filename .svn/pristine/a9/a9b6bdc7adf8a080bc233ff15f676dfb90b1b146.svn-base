package com.credit.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * Created by Michael Chan on 3/31/2017.
 */
@Entity
@Table(name = "tb_user_permission")
public class UserPermission
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "vkey")
    private String vKey;

    @Column(name = "daily_times")
    private Integer dailyTimes;

    @Column(name = "yearly_times")
    private Integer yearlyTimes;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "resource_id")
    private Integer resourceId;

    public Integer getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId)
    {
        this.permissionId = permissionId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getvKey()
    {
        return vKey;
    }

    public void setvKey(String vKey)
    {
        this.vKey = vKey;
    }

    public Integer getDailyTimes()
    {
        return dailyTimes;
    }

    public void setDailyTimes(Integer dailyTimes)
    {
        this.dailyTimes = dailyTimes;
    }

    public Integer getYearlyTimes()
    {
        return yearlyTimes;
    }

    public void setYearlyTimes(Integer yearlyTimes)
    {
        this.yearlyTimes = yearlyTimes;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Integer getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(Integer resourceId)
    {
        this.resourceId = resourceId;
    }
}
