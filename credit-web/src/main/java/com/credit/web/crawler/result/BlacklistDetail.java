package com.credit.web.crawler.result;

import java.util.Date;

/**
 * Created by wangjunling on 2017/3/30.
 */
public class BlacklistDetail
{
    private boolean isByBankMark;// 是否被银行机构标记为黑名单

    private boolean isByNotBankMark;// 是否被非银机构标记为黑名单

    private Date updateTime;// 黑名单更新日期

    public boolean isByBankMark()
    {
        return isByBankMark;
    }

    public void setByBankMark(boolean byBankMark)
    {
        isByBankMark = byBankMark;
    }

    public boolean isByNotBankMark()
    {
        return isByNotBankMark;
    }

    public void setByNotBankMark(boolean byNotBankMark)
    {
        isByNotBankMark = byNotBankMark;
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
