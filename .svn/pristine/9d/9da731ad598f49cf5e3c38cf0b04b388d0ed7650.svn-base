package com.credit.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Michael Chan on 4/13/2017.
 */
@Entity
@Table(name = "tb_query_contact")
public class QueryContact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "lid")
    private Integer lid;

    @Column(name = "cname")
    private String cname;

    @Column(name = "cnumber")
    private String cnumber;

    @Column(name = "relation")
    private String relation;

    @Column(name = "create_time")
    private Date createTime;

    public Integer getCid()
    {
        return cid;
    }

    public void setCid(Integer cid)
    {
        this.cid = cid;
    }

    public Integer getLid()
    {
        return lid;
    }

    public void setLid(Integer lid)
    {
        this.lid = lid;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getCnumber()
    {
        return cnumber;
    }

    public void setCnumber(String cnumber)
    {
        this.cnumber = cnumber;
    }

    public String getRelation()
    {
        return relation;
    }

    public void setRelation(String relation)
    {
        this.relation = relation;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
