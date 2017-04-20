package com.credit.web.crawler.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/30.
 */
public class OverdueClassifyDetail
{

    private String latestTime;// 最近一次逾期时间

    private MRecord M3 = new MRecord();

    private MRecord M6 = new MRecord();;

    private MRecord M9 = new MRecord();;

    private MRecord M12 = new MRecord();;

    private MRecord M24 = new MRecord();;

    public String getLatestTime()
    {
        return latestTime;
    }

    public void setLatestTime(String latestTime)
    {
        this.latestTime = latestTime;
    }

    public MRecord getM3()
    {
        return M3;
    }

    public void setM3(MRecord m3)
    {
        M3 = m3;
    }

    public MRecord getM6()
    {
        return M6;
    }

    public void setM6(MRecord m6)
    {
        M6 = m6;
    }

    public MRecord getM9()
    {
        return M9;
    }

    public void setM9(MRecord m9)
    {
        M9 = m9;
    }

    public MRecord getM12()
    {
        return M12;
    }

    public void setM12(MRecord m12)
    {
        M12 = m12;
    }

    public MRecord getM24()
    {
        return M24;
    }

    public void setM24(MRecord m24)
    {
        M24 = m24;
    }

    public class MRecord
    {
        private Record bankLoan = new Record();

        private Record bankCredit = new Record();

        private Record otherLoan = new Record();

        private Record otherCredit = new Record();

        public Record getBankLoan()
        {
            return bankLoan;
        }

        public void setBankLoan(Record bankLoan)
        {
            this.bankLoan = bankLoan;
        }

        public Record getBankCredit()
        {
            return bankCredit;
        }

        public void setBankCredit(Record bankCredit)
        {
            this.bankCredit = bankCredit;
        }

        public Record getOtherLoan()
        {
            return otherLoan;
        }

        public void setOtherLoan(Record otherLoan)
        {
            this.otherLoan = otherLoan;
        }

        public Record getOtherCredit()
        {
            return otherCredit;
        }

        public void setOtherCredit(Record otherCredit)
        {
            this.otherCredit = otherCredit;
        }
    }

    public class Record
    {
        private Integer recordNums; // 逾期记录的条数

        private Integer orgNums; // 涉及机构数

        private String maxAmount; // 最大逾期金额

        @JSONField(serialize = false)
        private Integer maxAmountEndNum;

        private String longestDays;// 最长逾期天数

        private String longestDaysTime;// 最长逾期天数的时间

        public Integer getMaxAmountEndNum()
        {
            return maxAmountEndNum;
        }

        public void setMaxAmountEndNum(Integer maxAmountEndNum)
        {
            this.maxAmountEndNum = maxAmountEndNum;
        }

        public Integer getRecordNums()
        {
            return recordNums;
        }

        public void setRecordNums(Integer recordNums)
        {
            this.recordNums = recordNums;
        }

        public Integer getOrgNums()
        {
            return orgNums;
        }

        public void setOrgNums(Integer orgNums)
        {
            this.orgNums = orgNums;
        }

        public String getMaxAmount()
        {
            return maxAmount;
        }

        public void setMaxAmount(String maxAmount)
        {
            this.maxAmount = maxAmount;
        }

        public String getLongestDays()
        {
            return longestDays;
        }

        public void setLongestDays(String longestDays)
        {
            this.longestDays = longestDays;
        }

        public String getLongestDaysTime()
        {
            return longestDaysTime;
        }

        public void setLongestDaysTime(String longestDaysTime)
        {
            this.longestDaysTime = longestDaysTime;
        }
    }

}