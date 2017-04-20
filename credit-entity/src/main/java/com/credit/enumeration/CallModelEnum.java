package com.credit.enumeration;

/**
 * Created by wangjunling on 2017/3/9.
 */
public enum CallModelEnum
{
    CALL_OUT("call_out", "主叫", "1"), CALL_IN("call_in", "被叫", "2"), CONTACT("contact", "联系", "4");

    private String name;

    private String comment;

    private String code;

    CallModelEnum(String name, String comment, String code)
    {
        this.name = name;
        this.comment = comment;
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public String getComment()
    {
        return comment;
    }

    public String getCode()
    {
        return code;
    }
}
