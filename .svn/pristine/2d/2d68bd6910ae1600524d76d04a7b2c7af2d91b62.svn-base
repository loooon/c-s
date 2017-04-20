package com.credit.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.credit.common.util.http.HttpUtil;

/**
 * Created by wangjunling on 2017/3/16.
 */
public class CallDetailsNumberMarkTest
{

    @Test
    public void testCallDetails()
    {
        String url = "http://localhost:8080/service?t=call_detail_number_mark";
        File sampleFileDir = new File("H:\\calldetails");
        Map<String, String> paramMap = null;
        Map<String, File> fileMap = null;
        for (File tmpFile : sampleFileDir.listFiles())
        {
            String fname = tmpFile.getName().replaceAll(".txt", "");
            String[] cols = fname.split("_");
            paramMap = new HashMap<String, String>();
            fileMap = new HashMap<>();
            fileMap.put("call_details", tmpFile);
            paramMap.put("phone", cols[0].trim());
            paramMap.put("name", cols[2].trim());
            paramMap.put("idcard", cols[1].trim());
            paramMap.put("imei", "");
            paramMap.put("imsi", "");
            paramMap.put("vkey", "20170314114300123");
            paramMap.put("contacts", "");
            paramMap.put("apply_date", "");
            paramMap.put("call_details", "");
            paramMap.put("file_charset", "");
            long s = System.currentTimeMillis();
            try
            {
                String result = HttpUtil.postReq(url, paramMap, fileMap);
                System.out.println(result);
                long e = System.currentTimeMillis();
                System.out.println(fname+"耗时：" + (e - s));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
