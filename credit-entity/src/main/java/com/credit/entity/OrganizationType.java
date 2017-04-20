package com.credit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Michael Chan on 3/10/2017.
 */
@Entity
@Table(name = "tb_org_type")
public class OrganizationType
{
    @Id
    @Column(name = "org_type")
    private Integer orgType;

    @Column(name = "org_type_name")
    private String orgTypeName;

    private OrganizationType(){}

    public OrganizationType(Integer orgType, String orgTypeName)
    {
        this.orgType = orgType;
        this.orgTypeName = orgTypeName;
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
}
