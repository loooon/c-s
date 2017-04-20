package com.credit.config;

import com.credit.common.context.PropertyFactory;

import java.util.List;

/**
 * Created by wangjunling on 2017/3/27.
 */
public class OrgTypeCfg extends BaseCfg
{
    private static String ORG_TYPE_IDS = "org.type.internet.finance.ids";

    public static List<Integer> getInternetFinanceIds()
    {
        String property = PropertyFactory.getProperty(ORG_TYPE_IDS);
        return castToIntegerList(property);
    }

}
