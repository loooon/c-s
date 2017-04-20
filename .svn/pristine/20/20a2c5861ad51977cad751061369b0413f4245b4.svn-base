package com.credit.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * Created by Michael Chan on 3/31/2017.
 */
@Entity
@Table(name = "tb_site_resource")
public class SiteResource
{
    public static int RESOURCE_STATUS_DELETE = 1;

    public static int RESOURCE_STATUS_DEFAULT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_path")
    private String resourcePath;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "resource_type")
    private Integer resourceType;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "resource_status")
    private Integer resourceStatus;

    public Integer getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(Integer resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourcePath()
    {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public Integer getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(Integer resourceType)
    {
        this.resourceType = resourceType;
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

    public Integer getResourceStatus()
    {
        return resourceStatus;
    }

    public void setResourceStatus(Integer resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }
}
