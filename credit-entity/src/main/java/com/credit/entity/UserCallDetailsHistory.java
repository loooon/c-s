package com.credit.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_call_details_history")
public class UserCallDetailsHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "search_user")
    private String searchUser;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "idcard")
    private String idcard;

    @Column(name = "apply_date")
    private String applyDate;

    @Column(name = "call_details_path")
    private String callDetailsPath;

    @Column(name = "file_md5_digest")
    private String fileMd5Digest;

    @Column(name = "contact_checks")
    private String contactChecks;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getSearchUser()
    {
        return searchUser;
    }

    public void setSearchUser(String searchUser)
    {
        this.searchUser = searchUser;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIdcard()
    {
        return idcard;
    }

    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getApplyDate()
    {
        return applyDate;
    }

    public void setApplyDate(String applyDate)
    {
        this.applyDate = applyDate;
    }

    public String getCallDetailsPath()
    {
        return callDetailsPath;
    }

    public void setCallDetailsPath(String callDetailsPath)
    {
        this.callDetailsPath = callDetailsPath;
    }

    public String getFileMd5Digest()
    {
        return fileMd5Digest;
    }

    public void setFileMd5Digest(String fileMd5Digest)
    {
        this.fileMd5Digest = fileMd5Digest;
    }

    public String getContactChecks()
    {
        return contactChecks;
    }

    public void setContactChecks(String contactChecks)
    {
        this.contactChecks = contactChecks;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
}
