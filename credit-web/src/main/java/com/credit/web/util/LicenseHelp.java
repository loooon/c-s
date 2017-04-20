package com.credit.web.util;

import com.credit.common.util.AESEncry;
import com.credit.common.util.time.TimeUtil;

import java.util.Date;

public final class LicenseHelp
{

    /**
     * 创建License
     * 
     * @param dueTime
     *            License到期时间戳，不能晚于当前时间
     */
    public static String createLicense(long dueTime) throws Exception
    {
        if (dueTime < System.currentTimeMillis())
        {
            throw new Exception("License到期时间不能晚于当前时间");
        }
        return AESEncry.getInstance().encrypt(String.valueOf(dueTime));
    }

    /**
     * 解密License
     * 
     * @param encryLicenseStr
     *            密文License
     * @result 解密后的明文License
     */
    public static String decryLicense(final String encryLicenseStr) throws Exception
    {
        if (encryLicenseStr == null || encryLicenseStr.trim().length() == 0)
        {
            throw new Exception("Lincense不能为空");
        }
        return AESEncry.getInstance().decrypt(encryLicenseStr);
    }

    public static void main(String[] args) throws Exception
    {
		Date futrueDate = TimeUtil.getFutrueDate(new Date(), 30);
		Date parse = TimeUtil.parse("2018-04-01 00:00:00", TimeUtil.FORMAT_NORMAL);
		long dueTime = parse.getTime();
		System.out.println("加密前time："+dueTime);
//		long dueTime = System.currentTimeMillis();
        String encryLicenseStr = createLicense(dueTime);
        System.out.println("密文License:" + encryLicenseStr);
        String decryLicenseStr = decryLicense(encryLicenseStr);
        System.out.println("明文License:" + decryLicenseStr);
    }
}
