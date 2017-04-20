package com.credit.web.crawler.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/30.
 */
public class LoanDetail
{
    private String latestRepaySuccessTime;

    private MRecord M3 = new MRecord();

    private MRecord M6 = new MRecord();

    private MRecord M9 = new MRecord();

    private MRecord M12 = new MRecord();

    private MRecord M24 = new MRecord();

    public String getLatestRepaySuccessTime()
    {
        return latestRepaySuccessTime;
    }

    public void setLatestRepaySuccessTime(String latestRepaySuccessTime)
    {
        this.latestRepaySuccessTime = latestRepaySuccessTime;
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
        private Record other = new Record();

        private Record bank = new Record();

        public Record getOther()
        {
            return other;
        }

        public void setOther(Record other)
        {
            this.other = other;
        }

        public Record getBank()
        {
            return bank;
        }

        public void setBank(Record bank)
        {
            this.bank = bank;
        }
    }

    public class Record
    {

        private Integer orgNums;// 涉及机构数

        private String loanAmount; // 贷款总金额

        @JSONField(serialize = false)
        private Integer loanAmountEndNum; // 贷款总金额

        private String totalAmount; // 月需还款总额

        @JSONField(serialize = false)
        private Integer totalAmountEndNum; // 贷款总金额

        private String repayAmount;// 月需还款最大金额

        @JSONField(serialize = false)
        private Integer repayAmountEndNum; // 月需还款最大金额

        private Integer statOrgNums; // 被其他机构查询的机构数

        private Integer statQueryNums; // 被其他机构查询的次数

        private String latestLoanTime;// 最近一次贷款时间

        public Integer getRepayAmountEndNum()
        {
            return repayAmountEndNum;
        }

        public void setRepayAmountEndNum(Integer repayAmountEndNum)
        {
            this.repayAmountEndNum = repayAmountEndNum;
        }

        public Integer getLoanAmountEndNum()
        {
            return loanAmountEndNum;
        }

        public void setLoanAmountEndNum(Integer loanAmountEndNum)
        {
            this.loanAmountEndNum = loanAmountEndNum;
        }

        public Integer getTotalAmountEndNum()
        {
            return totalAmountEndNum;
        }

        public void setTotalAmountEndNum(Integer totalAmountEndNum)
        {
            this.totalAmountEndNum = totalAmountEndNum;
        }

        public Integer getOrgNums()
        {
            return orgNums;
        }

        public void setOrgNums(Integer orgNums)
        {
            this.orgNums = orgNums;
        }

        public String getLoanAmount()
        {
            return loanAmount;
        }

        public void setLoanAmount(String loanAmount)
        {
            this.loanAmount = loanAmount;
        }

        public String getTotalAmount()
        {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount)
        {
            this.totalAmount = totalAmount;
        }

        public String getRepayAmount()
        {
            return repayAmount;
        }

        public void setRepayAmount(String repayAmount)
        {
            this.repayAmount = repayAmount;
        }

		public Integer getStatOrgNums() {
			return statOrgNums;
		}

		public void setStatOrgNums(Integer statOrgNums) {
			this.statOrgNums = statOrgNums;
		}

		public Integer getStatQueryNums() {
			return statQueryNums;
		}

		public void setStatQueryNums(Integer statQueryNums) {
			this.statQueryNums = statQueryNums;
		}

		public String getLatestLoanTime()
        {
            return latestLoanTime;
        }

        public void setLatestLoanTime(String latestLoanTime)
        {
            this.latestLoanTime = latestLoanTime;
        }
    }

}
