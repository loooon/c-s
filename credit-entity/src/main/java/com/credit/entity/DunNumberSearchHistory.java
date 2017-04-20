package com.credit.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by Michael Chan on 3/27/2017.
 */
@Entity
@Table(name = "tb_dun_number_search_history")
public class DunNumberSearchHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "search_phone")
    private String searchPhone;

    @Column(name = "search_ip")
    private String search_ip;

    @Column(name = "org_type")
    private Integer orgType;

    @Column(name = "org_type_name")
    private String orgTypeName;

    @Column(name = "demand_type")
    private Integer demandType;

    @Column(name = "demand_type_name")
    private String demandTypeName;

    @Column(name = "search_time")
    private Date searchTime;

    @Column(name = "update_time")
    private Date updateTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getSearchPhone()
    {
        return searchPhone;
    }

    public void setSearchPhone(String searchPhone)
    {
        this.searchPhone = searchPhone;
    }

    public String getSearch_ip()
    {
        return search_ip;
    }

    public void setSearch_ip(String search_ip)
    {
        this.search_ip = search_ip;
    }

    public Integer getOrgType()
    {
        return orgType;
    }

    public void setOrgType(Integer orgType)
    {
        this.orgType = orgType;
    }

    public String getOrgTypeName()
    {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName)
    {
        this.orgTypeName = orgTypeName;
    }

    public Integer getDemandType()
    {
        return demandType;
    }

    public void setDemandType(Integer demandType)
    {
        this.demandType = demandType;
    }

    public String getDemandTypeName()
    {
        return demandTypeName;
    }

    public void setDemandTypeName(String demandTypeName)
    {
        this.demandTypeName = demandTypeName;
    }

    public Date getSearchTime()
    {
        return searchTime;
    }

    public void setSearchTime(Date searchTime)
    {
        this.searchTime = searchTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
}
