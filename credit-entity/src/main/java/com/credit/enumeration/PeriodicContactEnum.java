package com.credit.enumeration;

import com.credit.config.CallDetailsCfg;

/**
 * Created by wangjunling on 2017/3/16.
 */
public enum PeriodicContactEnum
{
    YES("是"), NO("否"), DEFAULT("未知");

    private String text;

    PeriodicContactEnum(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public static String getPeriodicName(Double periodic)
    {
        if (periodic == null)
        {
			return NO.getText();
        }
        Double callPeriodic = CallDetailsCfg.getCallPeriodic();

        if (periodic > callPeriodic)
        {
            return YES.getText();
        }
        return NO.getText();
    }

	public static Boolean isPeriodicContact(Double periodic)
	{
		if (periodic == null)
		{
			return false;
		}
		Double callPeriodic = CallDetailsCfg.getCallPeriodic();

		if (periodic > callPeriodic)
		{
			return true;
		}
		return false;
	}
}