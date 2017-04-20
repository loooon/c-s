package com.credit.enumeration;

import com.credit.config.CallDetailsCfg;

/**
 * Created by Michael Chan on 3/16/2017.
 */
public enum StabilityEnum
{
    STABILITY("稳定"), INSTABILITY("不稳定");

    private String name;

    StabilityEnum(String name)
    {
        this.name = name;
    }

    public static String getStability(double avgCall)
    {
        if (CallDetailsCfg.getStabilityCutOffPoint() <= avgCall)
        {
            return STABILITY.getName();
        }
        return INSTABILITY.getName();
    }

    public String getName()
    {
        return name;
    }
}
