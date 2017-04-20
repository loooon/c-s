package com.credit.enumeration;

/**
 * Created by wangjunling on 2017/4/13.
 */
public enum GrayscaleTimeEnum
{
    WHOLE_DAY("wd", "全天"), EVENING("en", "晚上");

    private String name;

    private String description;

    GrayscaleTimeEnum(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}
