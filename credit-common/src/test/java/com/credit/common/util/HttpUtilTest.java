package com.credit.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.util.http.HttpUtil;

/**
 * Created by wangjunling on 2017/3/15.
 */
public class HttpUtilTest
{
    @Test
    public void postFileTest()
    {

/*        // 上传短信
        Map<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("sms",
                new File("D:\\IdeaProject\\credit-site\\credit-common\\src\\test\\resources\\sms_sample.txt"));
        String respResult = null;
        try
        {
            respResult = HttpUtil.postReq("http://192.168.108.103:5000/sms/parse", null, fileMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // System.out.println(respResult);
        JSONObject jsonObject = JSON.parseObject(respResult);
        String tradeNo = jsonObject.getString("data");
        System.out.println("交易编号:" + tradeNo);

        long start = System.currentTimeMillis();*/
/*        while (true)
        {
            // 获取结构化结果
            try
            {
                respResult = HttpUtil.getReq("http://192.168.108.103:5000/sms/task/" + tradeNo + "/json", null);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            JSONObject respJSONObj = JSON.parseObject(respResult);
            if (respJSONObj.getString("message").equals("任务尚在处理中"))
            {
                continue;
            }
            long end = System.currentTimeMillis();
            // System.out.println(respResult);
            System.out.println("耗时:" + (end - start));

        }*/
    }
}
