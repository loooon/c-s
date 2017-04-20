package com.credit.web.biz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

import com.alibaba.fastjson.JSON;
import com.credit.common.util.pki.SecureUtil;
import com.credit.web.crawler.BlacklistCrawler;
import com.credit.web.crawler.LoanCrawler;
import com.credit.web.crawler.OverdueCrawler;
import com.credit.web.crawler.result.BlacklistDetail;
import com.credit.web.crawler.result.LoanDetail;
import com.credit.web.crawler.result.OverdueClassifyDetail;

/**
 * Created by wangjunling on 2017/3/29.
 */
public class CreditLoanService
{
    private CreditLoanService()
    {
    }

    public static CreditLoanService getInstance()
    {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton
    {
        INSTANCE;

        private CreditLoanService creditLoanService;

        Singleton()
        {
            creditLoanService = new CreditLoanService();
        }

        public CreditLoanService getInstance()
        {
            return creditLoanService;
        }
    }

    private LoanCrawler loanCrawler = new LoanCrawler(new HttpClient());

    private OverdueCrawler overdueCrawler = new OverdueCrawler(new HttpClient());

    private BlacklistCrawler blacklistCrawler = new BlacklistCrawler(new HttpClient());

    public Map<String, Object> search(String phone, String idCard, String name, String imsi, String imei, String[] types)
            throws Exception
    {
        String overdueUrl = "https://i.trustutn.org/b/overdueClassify";
        String loanUrl = "https://i.trustutn.org/b/loanClassify";
        String blacklistUrl = "https://i.trustutn.org/b/blacklist";
        String pkey = "eff9173641f5f33cb8a261047ea74d3a";
        String ptime = String.valueOf(System.currentTimeMillis());
        String vkey = SecureUtil.md5(pkey + "_" + ptime + "_" + pkey);
        Map<String, String> param = new HashMap<String, String>();
        param.put("pname", "020160907001");
        param.put("ptime", ptime);
        param.put("vkey", vkey);
        param.put("phone", phone);
        param.put("idCard", idCard);
        param.put("name", name);
        param.put("imsi", imsi);
        param.put("imei", imei);
        Map<String, Object> result = new HashMap<>();
        List<String> typeList = Arrays.asList(types);
        if (typeList.contains("overdue"))
        {
            OverdueClassifyDetail overdueResult = overdueCrawler.search(overdueUrl, param);
            if (overdueResult != null)
            {
                result.put("overdueResult", overdueResult);
            }
        }
        if (typeList.contains("loan"))
        {
            LoanDetail loanResult = loanCrawler.search(loanUrl, param);
            if (loanResult != null)
            {
                result.put("loanResult", loanResult);
            }
        }
        if (typeList.contains("blacklist"))
        {
            BlacklistDetail blacklistResult = blacklistCrawler.search(blacklistUrl, param);
            if (blacklistResult != null)
            {
                result.put("blacklistResult", blacklistResult);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception
    {
        Map<String, Object> map = CreditLoanService.getInstance().search("13873365284", "432522199404266437", "样本", "",
                "866818022788879", new String[]{"overdue", "loan", "blacklist"});
        System.out.println(JSON.toJSONString(map));
    }
}
