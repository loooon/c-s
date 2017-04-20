package com.credit.web.model;


import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael Chan on 3/8/2017.
 */
public class UserLogin
{
    @NotBlank(message = "用户名不能为空")
    String name;

    @NotBlank(message = "密码不能为空")
    String password;

    String code;

    private String from;

    private Integer autoLogin;

    public Integer getAutoLogin()
    {
        return autoLogin;
    }

    public void setAutoLogin(Integer autoLogin)
    {
        this.autoLogin = autoLogin;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
