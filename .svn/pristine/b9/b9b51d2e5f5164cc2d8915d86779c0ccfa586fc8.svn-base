package com.credit.web.crawler;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.result.ResultJson;
import com.credit.common.util.ReUtil;
import com.credit.web.crawler.result.OverdueClassifyDetail;

/**
 * Created by wangjunling on 2017/3/30.
 */
public class OverdueCrawler extends AbstractBaseCrawler<OverdueClassifyDetail>
{

    public OverdueCrawler(HttpClient httpClient)
    {
        super(httpClient);
    }

    @Override
    protected OverdueClassifyDetail parseResult(String result) throws Exception
    {
        JSONObject resultObject = JSONObject.parseObject(result);
        Integer resultCode = resultObject.getInteger("result");
        if (resultCode != 0 || resultObject.getJSONObject("data") == null)
        {
            return null;
        }
        JSONArray recordArray = resultObject.getJSONObject("data").getJSONArray("record");
        if (recordArray == null)
        {
            return null;
        }
        OverdueClassifyDetail overdueClassifyData = new OverdueClassifyDetail();

        for (int i = 0; i < recordArray.size(); i++)
        {
            JSONObject record = recordArray.getJSONObject(i);
            String latestTimei = record.getString("latestTime");
            if (overdueClassifyData.getLatestTime() == null
                    || overdueClassifyData.getLatestTime().compareTo(latestTimei) == -1)
            {
                overdueClassifyData.setLatestTime(latestTimei);
            }
            JSONArray classification = record.getJSONArray("classification");
            if (classification == null)
            {
                continue;
            }
            compareClassification(classification, overdueClassifyData);
        }
        return overdueClassifyData;
    }

    private void compareClassification(JSONArray classification, OverdueClassifyDetail overdueClassifyData)
    {
        for (int i1 = 0; i1 < classification.size(); i1++)
        {
            JSONObject jsonObject = classification.getJSONObject(i1);
            JSONObject m3 = jsonObject.getJSONObject("M3");
            if (m3 != null)
            {
                compareMMonth(m3, overdueClassifyData.getM3());
            }
            JSONObject m6 = jsonObject.getJSONObject("M6");
            if (m6 != null)
            {
                compareMMonth(m6, overdueClassifyData.getM6());
            }
            JSONObject m9 = jsonObject.getJSONObject("M9");
            if (m9 != null)
            {
                compareMMonth(m9, overdueClassifyData.getM9());
            }
            JSONObject m12 = jsonObject.getJSONObject("M12");
            if (m12 != null)
            {
                compareMMonth(m12, overdueClassifyData.getM12());
            }
            JSONObject m24 = jsonObject.getJSONObject("M24");
            if (m24 != null)
            {
                compareMMonth(m24, overdueClassifyData.getM24());
            }

        }
    }

    private void compareMMonth(JSONObject m3, OverdueClassifyDetail.MRecord mRecord)
    {
        JSONObject bankLoan = m3.getJSONObject("bankLoan");
        if (bankLoan != null)
        {
            OverdueClassifyDetail.Record bankLoan1 = mRecord.getBankLoan();
            compareRecord(bankLoan, bankLoan1);
        }
        JSONObject bankCredit = m3.getJSONObject("bankCredit");
        if (bankCredit != null)
        {
            OverdueClassifyDetail.Record bankCredit1 = mRecord.getBankCredit();
            compareRecord(bankCredit, bankCredit1);
        }
        JSONObject otherLoan = m3.getJSONObject("otherLoan");
        if (otherLoan != null)
        {
            OverdueClassifyDetail.Record otherLoan1 = mRecord.getOtherLoan();
            compareRecord(otherLoan, otherLoan1);
        }
        JSONObject otherCredit = m3.getJSONObject("otherCredit");
        if (otherCredit != null)
        {
            OverdueClassifyDetail.Record otherCredit1 = mRecord.getOtherCredit();
            compareRecord(otherCredit, otherCredit1);
        }
    }

    private void compareRecord(JSONObject bankLoan, OverdueClassifyDetail.Record record)
    {

        Integer recordNums = bankLoan.getInteger("recordNums");
        if (recordNums != null && (record.getRecordNums() == null || record.getRecordNums() < recordNums))
        {
            record.setRecordNums(recordNums);
        }
        Integer orgNums = bankLoan.getInteger("orgNums");
        if (orgNums != null && (record.getOrgNums() == null || record.getOrgNums().compareTo(orgNums) == -1))
        {
            record.setOrgNums(orgNums);
        }
        String maxAmount = bankLoan.getString("maxAmount");
        if (StringUtils.isNotBlank(maxAmount))
        {
            String[] split = maxAmount.split(",");
            if (split.length > 1)
            {
                Integer endNum = Integer.valueOf(split[1].substring(0, split[1].length() - 1).trim());
                if (record.getMaxAmountEndNum() == null || record.getMaxAmountEndNum() < endNum)
                {
                    record.setMaxAmountEndNum(endNum);
                    record.setMaxAmount(maxAmount);
                }
            }
        }
        String longestDays = bankLoan.getString("longestDays");
        if (StringUtils.isNotBlank(longestDays))
        {
            String s = ReUtil.delFirst(ReUtil.RE_CHINESE, longestDays);
            Integer day = Integer.valueOf(s);
            if (StringUtils.isBlank(record.getLongestDays())
                    || Integer.valueOf(ReUtil.delFirst(ReUtil.RE_CHINESE, record.getLongestDays())) < day)
            {
                record.setLongestDays(longestDays);
            }
        }
        String longestDaysTime = bankLoan.getString("longestDaysTime");
        if (StringUtils.isNotBlank(longestDaysTime))
        {
            if (StringUtils.isBlank(record.getLongestDaysTime())
                    || record.getLongestDaysTime().compareTo(longestDaysTime) == -1)
            {
                record.setLongestDaysTime(longestDaysTime);
            }
        }
    }

    @Override
    protected Logger getLogger()
    {
        return null;
    }

    public static void main(String[] args)
    {
        String data = "\n"
                + "\n"
                + "{\"record\":[{\n"
                + "\"classification\":\n"
                + "[{\"M9\":{\"otherLoan\":{\"maxAmount\":\"(200, 500]\",\"longestDays\":\"\",\"recordNums\":2,\"orgNums\":1,\"longestDaysTime\":\"2016-03-05\"}}},{\"M12\":{\"otherLoan\":{\"maxAmount\":\"(200, 500]\",\"longestDays\":\"7天\",\"recordNums\":1,\"orgNums\":3}}},{\"M24\":{\"otherLoan\":{\"maxAmount\":\"(200, 500]\",\"longestDays\":\"10\",\"recordNums\":8,\"orgNums\":1}}}],\"matchId\":\"18975438610\",\"matchType\":\"phone\",\"matchValue\":\"FC72622AFE3096A1AD62CB481C488F87\",\"latestTime\":\"2016-08-18\"},{\n"
                + "\"classification\":\n"
                + "[{\"M9\":{\"otherLoan\":{\"maxAmount\":\"(500, 1000]\",\"longestDays\":\"\",\"recordNums\":2,\"orgNums\":2,\"longestDaysTime\":\"2016-08-30\"}}},{\"M12\":{\"otherLoan\":{\"maxAmount\":\"(300, 600]\",\"longestDays\":\"8天\",\"recordNums\":2,\"orgNums\":3}}},{\"M24\":{\"otherLoan\":{\"maxAmount\":\"(200, 500]\",\"longestDays\":\"7\",\"recordNums\":4,\"orgNums\":1}}}],\"matchId\":\"18975438610\",\"matchType\":\"phone\",\"matchValue\":\"FC72622AFE3096A1AD62CB481C488F87\",\"latestTime\":\"2016-08-18\"},{\n"
                + "\"classification\":\n"
                + "[{\"M9\":{\"otherLoan\":{\"maxAmount\":\"(1000, 2000]\",\"longestDays\":\"\",\"recordNums\":2,\"orgNums\":2,\"longestDaysTime\":\"2017-03-30\"}}},{\"M12\":{\"otherLoan\":{\"maxAmount\":\"(600, 800]\",\"longestDays\":\"9天\",\"recordNums\":4,\"orgNums\":3}}},{\"M24\":{\"otherLoan\":{\"maxAmount\":\"(200, 500]\",\"longestDays\":\"7\",\"recordNums\":3,\"orgNums\":10}}}],\"matchId\":\"18975438610\",\"matchType\":\"phone\",\"matchValue\":\"FC72622AFE3096A1AD62CB481C488F87\",\"latestTime\":\"2016-08-18\"}],\n"
                + "\"phone\":\"18975438610\",\"imei\":\"\",\"imsi\":\"460030877371602\"}";

        OverdueCrawler crawler = new OverdueCrawler(new HttpClient());
        try
        {
            ResultJson resultJson = new ResultJson();
            resultJson.setData(JSONObject.parseObject(data));
            resultJson.setResult(0);

            OverdueClassifyDetail overdueClassifyData = crawler.parseResult(JSON.toJSONString(resultJson));
            System.out.println(JSON.toJSONString(overdueClassifyData));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
