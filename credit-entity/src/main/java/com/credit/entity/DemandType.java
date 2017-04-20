package com.credit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Michael Chan on 3/10/2017.
 */
@Entity
@Table(name = "tb_demand_type")
public class DemandType
{
    @Id
    @Column(name = "demand_type")
    private Integer demandType;

    @Column(name = "demand_type_name")
    private String demandTypeName;

    private DemandType(){}

    public DemandType(Integer demandType, String demandTypeName)
    {
        this.demandType = demandType;
        this.demandTypeName = demandTypeName;
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
}