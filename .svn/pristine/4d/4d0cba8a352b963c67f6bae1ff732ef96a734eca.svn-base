package com.credit.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * Created by Michael Chan on 3/14/2017.
 */
@Entity
@Table(name = "tb_user_auth_ip")
public class UserAuthIp
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_vkey")
    private String userVkey;

    @Column(name = "authorized_ip")
    private String authorizedIp;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserVkey()
    {
        return userVkey;
    }

    public void setUserVkey(String userVkey)
    {
        this.userVkey = userVkey;
    }

    public String getAuthorizedIp()
    {
        return authorizedIp;
    }

    public void setAuthorizedIp(String authorizedIp)
    {
        this.authorizedIp = authorizedIp;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
}
