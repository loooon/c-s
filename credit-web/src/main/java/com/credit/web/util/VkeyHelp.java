package com.credit.web.util;

import com.credit.common.util.RandomUtil;
import com.credit.common.util.time.TimeUtil;
import org.hibernate.exception.DataException;

import java.util.Date;

/**
 * Created by Michael Chan on 3/30/2017.
 */
public final class VkeyHelp
{
    public static String generateVkey(Date currentTime) throws Exception
    {
        return TimeUtil.format(currentTime, "yyyyMMddhhmmss") + RandomUtil.genRandomNumberString(3);
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println(generateVkey(new Date()));;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
