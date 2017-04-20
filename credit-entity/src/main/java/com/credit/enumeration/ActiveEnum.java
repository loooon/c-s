package com.credit.enumeration;

import com.credit.config.CallDetailsCfg;

/**
 * Created by Michael Chan on 3/16/2017.
 */
public enum ActiveEnum
{
    ACTIVE("活跃"), NORMAL("一般"), INACTIVE("不活跃");

    private String name;

    ActiveEnum(String name )
    {
        this.name = name;
    }

    public static String getActive(double avgContact)
    {
        if (CallDetailsCfg.getAcitveCutOffPoint() <= avgContact)
        {
            return ACTIVE.getName();
        }
        else
            if (CallDetailsCfg.getNormalCutOffPoint() <= avgContact)
            {
                return NORMAL.getName();
            }
        return INACTIVE.getName();
    }

    public String getName()
    {
        return name;
    }
}
