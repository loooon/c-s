package com.credit.web.crawler;

import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.result.ResultJson;
import com.credit.common.util.time.TimeUtil;
import com.credit.web.crawler.result.BlacklistDetail;

/**
 * Created by wangjunling on 2017/3/30.
 */
public class BlacklistCrawler extends AbstractBaseCrawler<BlacklistDetail>
{
    public BlacklistCrawler(HttpClient httpClient)
    {
        super(httpClient);
    }

    @Override
    protected BlacklistDetail parseResult(String result) throws Exception
    {
        if (StringUtils.isBlank(result))
        {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer resultCode = jsonObject.getInteger("result");
        if (resultCode != 0 || jsonObject.getJSONObject("data") == null)
        {
            return null;
        }
        JSONArray others = jsonObject.getJSONObject("data").getJSONArray("others");
        if (others == null)
        {
            return null;
        }
        BlacklistDetail blacklistDetail = new BlacklistDetail();
        for (int i = 0; i < others.size(); i++)
        {
            JSONObject jsonObject1 = others.getJSONObject(i);
            String orgLostContact = jsonObject1.getString("orgLostContact");
            if (StringUtils.isNotBlank(orgLostContact))
            {
                blacklistDetail.setByNotBankMark(true);
            }
            String bankLostContact = jsonObject1.getString("bankLostContact");
            if (StringUtils.isNotBlank(bankLostContact))
            {
                blacklistDetail.setByBankMark(true);
            }
            String seriousOverdueTime = jsonObject1.getString("seriousOverdueTime");
            if (StringUtils.isNotBlank(seriousOverdueTime))
            {
                Date parse = parse(seriousOverdueTime);
                if (blacklistDetail.getUpdateTime() == null
                        || (parse != null && blacklistDetail.getUpdateTime().compareTo(parse) == -1))
                {
                    blacklistDetail.setUpdateTime(parse);
                }

            }
            String dunTelCallTime = jsonObject1.getString("dunTelCallTime");
            if (StringUtils.isNotBlank(dunTelCallTime))
            {
                Date parse = parse(dunTelCallTime);
                if (blacklistDetail.getUpdateTime() == null
                        || (parse != null && blacklistDetail.getUpdateTime().compareTo(parse) == -1))
                {
                    blacklistDetail.setUpdateTime(parse);
                }
            }
        }
        return blacklistDetail;
    }

    private Date parse(String dateStr)
    {
        Date parse = null;
        try
        {
            parse = TimeUtil.parse(dateStr, TimeUtil.FORMAT_DATE_ONLY);
        }
        catch (Exception e)
        {
            try
            {
                parse = TimeUtil.parse(dateStr, TimeUtil.FORMAT_YYYYMMDD);
            }
            catch (Exception e1)
            {

            }
        }
        return parse;
    }

    @Override
    protected Logger getLogger()
    {
        return LoggerFactory.getLogger(this.getClass());
    }

    public static void main(String[] args)
    {
        String data = "{\"idCard\":\"432522199404266437\",\"phone\":\"13873365284\",\"others\":[{\"dunTelCallTime\":\"20160120\",\"matchType\":\"phone\",\"matchValue\":\"13873365284\",\"seriousOverdueTime\":\"2016-04-24 11:44:20\",\"orgLostContact\":\"2015-09-11 09:49:52\",\"matchId\":\"1AE58ABF7660EC4E23BDBFBEF2267DB1\"},{\"dunTelCallTime\":\"20160120\",\"matchType\":\"phone\",\"matchValue\":\"13873365284\",\"orgBlackList\":[],\"seriousOverdueTime\":\"20150815\",\"matchId\":\"1AE58ABF7660EC4E23BDBFBEF2267DB1\"}],\"areaCode\":\"\",\"name\":\"样本\",\"imei\":\"866818022788879\",\"imsi\":\"\"}";

        BlacklistCrawler crawler = new BlacklistCrawler(new HttpClient());
        try
        {
            ResultJson resultJson = new ResultJson();
            resultJson.setData(JSONObject.parseObject(data));
            resultJson.setResult(0);

            BlacklistDetail overdueClassifyData = crawler.parseResult(JSON.toJSONString(resultJson));
            System.out.println(JSON.toJSONString(overdueClassifyData));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
