package com.credit.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_grayscale_stat_variable")
public class UserGrayscaleStatVariable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "variable_id")
    private GrayscaleVariable grayscaleVariable;

    @Column(name = "variable_date")
    private String variableDate;

    @Column(name = "variable_time")
    private String variableTime;

    @Column(name = "variable_dun")
    private String variableDun;

    @Column(name = "user_vkey")
    private String userVkey;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_time")
    private Date createTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public GrayscaleVariable getGrayscaleVariable()
    {
        return grayscaleVariable;
    }

    public void setGrayscaleVariable(GrayscaleVariable grayscaleVariable)
    {
        this.grayscaleVariable = grayscaleVariable;
    }

    public String getVariableDun()
    {
        return variableDun;
    }

    public void setVariableDun(String variableDun)
    {
        this.variableDun = variableDun;
    }

    public String getVariableDate()
    {
        return variableDate;
    }

    public void setVariableDate(String variableDate)
    {
        this.variableDate = variableDate;
    }

    public String getVariableTime()
    {
        return variableTime;
    }

    public void setVariableTime(String variableTime)
    {
        this.variableTime = variableTime;
    }

    public String getUserVkey()
    {
        return userVkey;
    }

    public void setUserVkey(String userVkey)
    {
        this.userVkey = userVkey;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
