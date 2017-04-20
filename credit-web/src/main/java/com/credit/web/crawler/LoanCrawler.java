package com.credit.web.crawler;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.result.ResultJson;
import com.credit.web.crawler.result.LoanDetail;

/**
 * Created by wangjunling on 2017/3/30.
 */
public class LoanCrawler extends AbstractBaseCrawler<LoanDetail>
{
    public LoanCrawler(HttpClient httpClient)
    {
        super(httpClient);
    }

    @Override
    protected LoanDetail parseResult(String result) throws Exception
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
        LoanDetail loanDetail = new LoanDetail();
        for (int i = 0; i < recordArray.size(); i++)
        {
            JSONObject record = recordArray.getJSONObject(i);
            String latestTimei = record.getString("latestRepaySuccessTime");
            if (loanDetail.getLatestRepaySuccessTime() == null
                    || loanDetail.getLatestRepaySuccessTime().compareTo(latestTimei) == -1)
            {
                loanDetail.setLatestRepaySuccessTime(latestTimei);
            }
            JSONArray classification = record.getJSONArray("classification");
            if (classification == null)
            {
                continue;
            }
            compareClassification(classification, loanDetail);
        }
        return loanDetail;
    }

    private void compareClassification(JSONArray classification, LoanDetail loanDetail)
    {
        for (int i1 = 0; i1 < classification.size(); i1++)
        {
            JSONObject jsonObject = classification.getJSONObject(i1);
            JSONObject m3 = jsonObject.getJSONObject("M3");
            if (m3 != null)
            {
                compareMMonth(m3, loanDetail.getM3());
            }
            JSONObject m6 = jsonObject.getJSONObject("M6");
            if (m6 != null)
            {
                compareMMonth(m6, loanDetail.getM6());
            }
            JSONObject m9 = jsonObject.getJSONObject("M9");
            if (m9 != null)
            {
                compareMMonth(m9, loanDetail.getM9());
            }
            JSONObject m12 = jsonObject.getJSONObject("M12");
            if (m12 != null)
            {
                compareMMonth(m12, loanDetail.getM12());
            }
            JSONObject m24 = jsonObject.getJSONObject("M24");
            if (m24 != null)
            {
                compareMMonth(m24, loanDetail.getM24());
            }

        }
    }

    private void compareMMonth(JSONObject m3, LoanDetail.MRecord mRecord)
    {
        JSONObject bankLoan = m3.getJSONObject("other");
        if (bankLoan != null)
        {
            LoanDetail.Record bankLoan1 = mRecord.getOther();
            compareRecord(bankLoan, bankLoan1);
        }
        JSONObject bankCredit = m3.getJSONObject("bank");
        if (bankCredit != null)
        {
            LoanDetail.Record bankCredit1 = mRecord.getBank();
            compareRecord(bankCredit, bankCredit1);
        }
    }

    private void compareRecord(JSONObject bankLoan, LoanDetail.Record record)
    {

        Integer orgNums = bankLoan.getInteger("orgNums");

        if (orgNums != null && (record.getOrgNums() == null || record.getOrgNums().compareTo(orgNums) == -1))
        {
            record.setOrgNums(orgNums);
        }

        String loanAmount = bankLoan.getString("loanAmount");
        if (StringUtils.isNotBlank(loanAmount))
        {
            String[] split = loanAmount.split(",");
            if (split.length > 1)
            {
                Integer endNum = Integer.valueOf(split[1].substring(0, split[1].length() - 1).trim());
                if (record.getLoanAmountEndNum() == null || record.getLoanAmountEndNum() < endNum)
                {
                    record.setLoanAmountEndNum(endNum);
                    record.setLoanAmount(loanAmount);
                }
            }
        }
        String totalAmount = bankLoan.getString("totalAmount");
        if (StringUtils.isNotBlank(totalAmount))
        {
            String[] split = totalAmount.split(",");
            if (split.length > 1)
            {
                Integer endNum = Integer.valueOf(split[1].substring(0, split[1].length() - 1).trim());
                if (record.getTotalAmountEndNum() == null || record.getTotalAmountEndNum() < endNum)
                {
                    record.setTotalAmountEndNum(endNum);
                    record.setTotalAmount(totalAmount);
                }
            }
        }

        String repayAmount = bankLoan.getString("repayAmount");
        if (StringUtils.isNotBlank(repayAmount))
        {
            String[] split = repayAmount.split(",");
            if (split.length > 1)
            {
                Integer endNum = Integer.valueOf(split[1].substring(0, split[1].length() - 1).trim());
                if (record.getRepayAmountEndNum() == null || record.getRepayAmountEndNum() < endNum)
                {
                    record.setRepayAmountEndNum(endNum);
                    record.setRepayAmount(totalAmount);
                }
            }
        }

		Integer statOrgNums = bankLoan.getInteger("statOrgNums");

		if (statOrgNums != null && (record.getStatOrgNums() == null || record.getStatOrgNums().compareTo(statOrgNums) == -1))
		{
			record.setStatOrgNums(statOrgNums);
		}
		Integer statQueryNums = bankLoan.getInteger("statQueryNums");

		if (statQueryNums != null && (record.getStatQueryNums() == null || record.getStatQueryNums().compareTo(statQueryNums) == -1))
		{
			record.setStatQueryNums(statQueryNums);
		}
        String latestLoanTime = bankLoan.getString("latestLoanTime");
        if (StringUtils.isNotBlank(latestLoanTime))
        {
            if (StringUtils.isBlank(record.getLatestLoanTime())
                    || record.getLatestLoanTime().compareTo(latestLoanTime) == -1)
            {
                record.setLatestLoanTime(latestLoanTime);
            }
        }
    }

    @Override
    protected Logger getLogger()
    {
        return LoggerFactory.getLogger(this.getClass());
    }

    public static void main(String[] args)
    {
        String data = "{\"record\":[{\"classification\":[{\"M12\":{\"other\":{\"loanAmount\":\"(2000, 3000]\",\"totalAmount\":\"(4000, 5000]\",\"repayAmount\":\"(4000, 5000]\",\"orgNums\":2}}}],\"matchId\":\"E7E2409EE96C575467F5A57B20B12C1D\",\"matchType\":\"phone\",\"matchValue\":\"17750532160\"},{\"classification\":[{\"M24\":{\"other\":{\"totalAmount\":\"(0, 200]\",\"repayAmount\":\"(0, 200]\",\"orgNums\":1}}}],\"matchId\":\"BEBBAA9B1DE8DFBF69B260652F498BF4\",\"matchType\":\"imei\",\"matchValue\":\"99000645063986\"},{\"classification\":[{\"M12\":{\"other\":{\"statOrgNums\":0,\"statQueryNums\":0}}},{\"M6\":{\"other\":{\"statOrgNums\":6,\"statQueryNums\":13}}},{\"M24\":{\"other\":{\"statOrgNums\":0,\"statQueryNums\":0}}},{\"M9\":{\"other\":{\"statOrgNums\":0,\"statQueryNums\":0}}},{\"M3\":{\"other\":{\"statOrgNums\":2,\"statQueryNums\":4}}}],\"matchId\":\"17750532160\",\"matchType\":\"phone\",\"matchValue\":\"A7347505AC9296575BB5B02A96515775\"}],\"phone\":\"17750532160\",\"imei\":\"99000645063986\",\"imsi\":\"\"}";

        LoanCrawler crawler = new LoanCrawler(new HttpClient());
        try
        {
            ResultJson resultJson = new ResultJson();
            resultJson.setData(JSONObject.parseObject(data));
            resultJson.setResult(0);

            LoanDetail loanDetail = crawler.parseResult(JSON.toJSONString(resultJson));
            System.out.println(JSON.toJSONString(loanDetail));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
