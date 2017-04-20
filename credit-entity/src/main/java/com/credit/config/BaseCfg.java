package com.credit.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjunling on 2017/3/27.
 */
public class BaseCfg
{

    protected static Double castToDouble(String property)
    {
        if (property == null || property.equals(""))
        {
            return 0d;
        }
        return Double.valueOf(property);
    }

    protected static Integer castToInteger(String property)
    {
        if (property == null || property.equals(""))
        {
            return 0;
        }
        return Integer.valueOf(property);
    }

    protected static List<Integer> castToIntegerList(String property)
    {
        if (property == null || property.equals(""))
        {
            return null;
        }
        String[] split = property.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++)
        {
            list.add(Integer.parseInt(split[i]));
        }
        return list;
    }

}
