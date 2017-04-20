package com.credit.web.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangjunling on 2017/3/29.
 */
public class GrayscaleStat
{
    private Integer numsCon; // 联系人总个数

    @JSONField(name = "allwdcn_TTimesOut")
    private Integer allwdcn_TTimesOut = 0;// 所有主叫催收号码次数

    @JSONField(name = "allwdcn_TTimesIn")
    private Integer allwdcn_TTimesIn = 0;// 所有被催收号码呼叫次数

    @JSONField(name = "allwdcn_TNumsCon")
    private Integer allwdcn_TNumsCon = 0;// 所有涉及催收号码总个数

    @JSONField(name = "allwdcn_TNumsCon_org")
    private Integer allwdcn_TNumsCon_org = 0;// 所有涉及催收号码总机构数

    @JSONField(name = "allwdcn_TNumsCon_bank")
    private Integer allwdcn_TNumsCon_bank = 0;// 所有涉及银行机构数

    @JSONField(name = "allwdcn_TNumsCon_cf")
    private Integer allwdcn_TNumsCon_cf = 0;// 所有涉及消费金融机构数

    @JSONField(name = "allwdcn_TNumsCon_f")
    private Integer allwdcn_TNumsCon_f = 0;// 所有涉及催收机构数

    @JSONField(name = "allwdcn_TNumsCon_if")
    private Integer allwdcn_TNumsCon_if = 0;// 所有涉及互联网金融机构数

    //

    @JSONField(name = "l1wwdcn_TTimesOut")
    private Integer l1wwdcn_TTimesOut = 0;// 近一周主叫催收号码次数

    @JSONField(name = "l1wwdcn_TTimesIn")
    private Integer l1wwdcn_TTimesIn = 0;// 近一周被催收号码呼叫次数

    @JSONField(name = "l1wwdcn_TNumsCon")
    private Integer l1wwdcn_TNumsCon = 0;// 近一周涉及催收号码总个数

    @JSONField(name = "l1wwdcn_TNumsCon_org")
    private Integer l1wwdcn_TNumsCon_org = 0;// 近一周涉及催收号码总机构数

    @JSONField(name = "l1wwdcn_TNumsCon_bank")
    private Integer l1wwdcn_TNumsCon_bank = 0;// 近一周涉及银行机构数

    @JSONField(name = "l1wwdcn_TNumsCon_cf")
    private Integer l1wwdcn_TNumsCon_cf = 0;// 近一周涉及消费金融机构数

    @JSONField(name = "l1wwdcn_TNumsCon_f")
    private Integer l1wwdcn_TNumsCon_f = 0;// 近一周涉及催收机构数

    @JSONField(name = "l1wwdcn_TNumsCon_if")
    private Integer l1wwdcn_TNumsCon_if = 0;// 近一周涉及互联网金融机构数

    //

    @JSONField(name = "l1mwdcn_TTimesOut")
    private Integer l1mwdcn_TTimesOut = 0;// 近一月主叫催收号码次数

    @JSONField(name = "l1mwdcn_TTimesIn")
    private Integer l1mwdcn_TTimesIn = 0;// 近一月被催收号码呼叫次数

    @JSONField(name = "l1mwdcn_TNumsCon")
    private Integer l1mwdcn_TNumsCon = 0;// 近一月涉及催收号码总个数

    @JSONField(name = "l1mwdcn_TNumsCon_org")
    private Integer l1mwdcn_TNumsCon_org = 0;// 近一月涉及催收号码总机构数

    @JSONField(name = "l1mwdcn_TNumsCon_bank")
    private Integer l1mwdcn_TNumsCon_bank = 0;// 近一月涉及银行机构数

    @JSONField(name = "l1mwdcn_TNumsCon_cf")
    private Integer l1mwdcn_TNumsCon_cf = 0;// 近一月涉及消费金融机构数

    @JSONField(name = "l1mwdcn_TNumsCon_f")
    private Integer l1mwdcn_TNumsCon_f = 0;// 近一月涉及催收机构数

    @JSONField(name = "l1mwdcn_TNumsCon_if")
    private Integer l1mwdcn_TNumsCon_if = 0;// 近一月涉及互联网金融机构数

    //

    @JSONField(name = "l3mwdcn_TTimesOut")
    private Integer l3mwdcn_TTimesOut = 0;// 近三个月主叫催收号码次数

    @JSONField(name = "l3mwdcn_TTimesIn")
    private Integer l3mwdcn_TTimesIn = 0;// 近三个月被催收号码呼叫次数

    @JSONField(name = "l3mwdcn_TNumsCon")
    private Integer l3mwdcn_TNumsCon = 0;// 近三个月涉及催收号码总个数

    @JSONField(name = "l3mwdcn_TNumsCon_org")
    private Integer l3mwdcn_TNumsCon_org = 0;// 近三个月涉及催收号码总机构数

    @JSONField(name = "l3mwdcn_TNumsCon_bank")
    private Integer l3mwdcn_TNumsCon_bank = 0;// 近三个月涉及银行机构数

    @JSONField(name = "l3mwdcn_TNumsCon_cf")
    private Integer l3mwdcn_TNumsCon_cf = 0;// 近三个月涉及消费金融机构数

    @JSONField(name = "l3mwdcn_TNumsCon_f")
    private Integer l3mwdcn_TNumsCon_f = 0;// 近三个月涉及催收机构数

    @JSONField(name = "l3mwdcn_TNumsCon_if")
    private Integer l3mwdcn_TNumsCon_if = 0;// 近三个月涉及互联网金融机构数

    public Integer getNumsCon()
    {
        return numsCon;
    }

    public void setNumsCon(Integer numsCon)
    {
        this.numsCon = numsCon;
    }

    public Integer getAllwdcn_TTimesOut()
    {
        return allwdcn_TTimesOut;
    }

    public void setAllwdcn_TTimesOut(Integer allwdcn_TTimesOut)
    {
        this.allwdcn_TTimesOut = allwdcn_TTimesOut;
    }

    public Integer getAllwdcn_TTimesIn()
    {
        return allwdcn_TTimesIn;
    }

    public void setAllwdcn_TTimesIn(Integer allwdcn_TTimesIn)
    {
        this.allwdcn_TTimesIn = allwdcn_TTimesIn;
    }

    public Integer getAllwdcn_TNumsCon()
    {
        return allwdcn_TNumsCon;
    }

    public void setAllwdcn_TNumsCon(Integer allwdcn_TNumsCon)
    {
        this.allwdcn_TNumsCon = allwdcn_TNumsCon;
    }

    public Integer getAllwdcn_TNumsCon_org()
    {
        return allwdcn_TNumsCon_org;
    }

    public void setAllwdcn_TNumsCon_org(Integer allwdcn_TNumsCon_org)
    {
        this.allwdcn_TNumsCon_org = allwdcn_TNumsCon_org;
    }

    public Integer getAllwdcn_TNumsCon_bank()
    {
        return allwdcn_TNumsCon_bank;
    }

    public void setAllwdcn_TNumsCon_bank(Integer allwdcn_TNumsCon_bank)
    {
        this.allwdcn_TNumsCon_bank = allwdcn_TNumsCon_bank;
    }

    public Integer getAllwdcn_TNumsCon_cf()
    {
        return allwdcn_TNumsCon_cf;
    }

    public void setAllwdcn_TNumsCon_cf(Integer allwdcn_TNumsCon_cf)
    {
        this.allwdcn_TNumsCon_cf = allwdcn_TNumsCon_cf;
    }

    public Integer getAllwdcn_TNumsCon_f()
    {
        return allwdcn_TNumsCon_f;
    }

    public void setAllwdcn_TNumsCon_f(Integer allwdcn_TNumsCon_f)
    {
        this.allwdcn_TNumsCon_f = allwdcn_TNumsCon_f;
    }

    public Integer getAllwdcn_TNumsCon_if()
    {
        return allwdcn_TNumsCon_if;
    }

    public void setAllwdcn_TNumsCon_if(Integer allwdcn_TNumsCon_if)
    {
        this.allwdcn_TNumsCon_if = allwdcn_TNumsCon_if;
    }

    public Integer getL1wwdcn_TTimesOut()
    {
        return l1wwdcn_TTimesOut;
    }

    public void setL1wwdcn_TTimesOut(Integer l1wwdcn_TTimesOut)
    {
        this.l1wwdcn_TTimesOut = l1wwdcn_TTimesOut;
    }

    public Integer getL1wwdcn_TTimesIn()
    {
        return l1wwdcn_TTimesIn;
    }

    public void setL1wwdcn_TTimesIn(Integer l1wwdcn_TTimesIn)
    {
        this.l1wwdcn_TTimesIn = l1wwdcn_TTimesIn;
    }

    public Integer getL1wwdcn_TNumsCon()
    {
        return l1wwdcn_TNumsCon;
    }

    public void setL1wwdcn_TNumsCon(Integer l1wwdcn_TNumsCon)
    {
        this.l1wwdcn_TNumsCon = l1wwdcn_TNumsCon;
    }

    public Integer getL1wwdcn_TNumsCon_org()
    {
        return l1wwdcn_TNumsCon_org;
    }

    public void setL1wwdcn_TNumsCon_org(Integer l1wwdcn_TNumsCon_org)
    {
        this.l1wwdcn_TNumsCon_org = l1wwdcn_TNumsCon_org;
    }

    public Integer getL1wwdcn_TNumsCon_bank()
    {
        return l1wwdcn_TNumsCon_bank;
    }

    public void setL1wwdcn_TNumsCon_bank(Integer l1wwdcn_TNumsCon_bank)
    {
        this.l1wwdcn_TNumsCon_bank = l1wwdcn_TNumsCon_bank;
    }

    public Integer getL1wwdcn_TNumsCon_cf()
    {
        return l1wwdcn_TNumsCon_cf;
    }

    public void setL1wwdcn_TNumsCon_cf(Integer l1wwdcn_TNumsCon_cf)
    {
        this.l1wwdcn_TNumsCon_cf = l1wwdcn_TNumsCon_cf;
    }

    public Integer getL1wwdcn_TNumsCon_f()
    {
        return l1wwdcn_TNumsCon_f;
    }

    public void setL1wwdcn_TNumsCon_f(Integer l1wwdcn_TNumsCon_f)
    {
        this.l1wwdcn_TNumsCon_f = l1wwdcn_TNumsCon_f;
    }

    public Integer getL1wwdcn_TNumsCon_if()
    {
        return l1wwdcn_TNumsCon_if;
    }

    public void setL1wwdcn_TNumsCon_if(Integer l1wwdcn_TNumsCon_if)
    {
        this.l1wwdcn_TNumsCon_if = l1wwdcn_TNumsCon_if;
    }

    public Integer getL1mwdcn_TTimesOut()
    {
        return l1mwdcn_TTimesOut;
    }

    public void setL1mwdcn_TTimesOut(Integer l1mwdcn_TTimesOut)
    {
        this.l1mwdcn_TTimesOut = l1mwdcn_TTimesOut;
    }

    public Integer getL1mwdcn_TTimesIn()
    {
        return l1mwdcn_TTimesIn;
    }

    public void setL1mwdcn_TTimesIn(Integer l1mwdcn_TTimesIn)
    {
        this.l1mwdcn_TTimesIn = l1mwdcn_TTimesIn;
    }

    public Integer getL1mwdcn_TNumsCon()
    {
        return l1mwdcn_TNumsCon;
    }

    public void setL1mwdcn_TNumsCon(Integer l1mwdcn_TNumsCon)
    {
        this.l1mwdcn_TNumsCon = l1mwdcn_TNumsCon;
    }

    public Integer getL1mwdcn_TNumsCon_org()
    {
        return l1mwdcn_TNumsCon_org;
    }

    public void setL1mwdcn_TNumsCon_org(Integer l1mwdcn_TNumsCon_org)
    {
        this.l1mwdcn_TNumsCon_org = l1mwdcn_TNumsCon_org;
    }

    public Integer getL1mwdcn_TNumsCon_bank()
    {
        return l1mwdcn_TNumsCon_bank;
    }

    public void setL1mwdcn_TNumsCon_bank(Integer l1mwdcn_TNumsCon_bank)
    {
        this.l1mwdcn_TNumsCon_bank = l1mwdcn_TNumsCon_bank;
    }

    public Integer getL1mwdcn_TNumsCon_cf()
    {
        return l1mwdcn_TNumsCon_cf;
    }

    public void setL1mwdcn_TNumsCon_cf(Integer l1mwdcn_TNumsCon_cf)
    {
        this.l1mwdcn_TNumsCon_cf = l1mwdcn_TNumsCon_cf;
    }

    public Integer getL1mwdcn_TNumsCon_f()
    {
        return l1mwdcn_TNumsCon_f;
    }

    public void setL1mwdcn_TNumsCon_f(Integer l1mwdcn_TNumsCon_f)
    {
        this.l1mwdcn_TNumsCon_f = l1mwdcn_TNumsCon_f;
    }

    public Integer getL1mwdcn_TNumsCon_if()
    {
        return l1mwdcn_TNumsCon_if;
    }

    public void setL1mwdcn_TNumsCon_if(Integer l1mwdcn_TNumsCon_if)
    {
        this.l1mwdcn_TNumsCon_if = l1mwdcn_TNumsCon_if;
    }

    public Integer getL3mwdcn_TTimesOut()
    {
        return l3mwdcn_TTimesOut;
    }

    public void setL3mwdcn_TTimesOut(Integer l3mwdcn_TTimesOut)
    {
        this.l3mwdcn_TTimesOut = l3mwdcn_TTimesOut;
    }

    public Integer getL3mwdcn_TTimesIn()
    {
        return l3mwdcn_TTimesIn;
    }

    public void setL3mwdcn_TTimesIn(Integer l3mwdcn_TTimesIn)
    {
        this.l3mwdcn_TTimesIn = l3mwdcn_TTimesIn;
    }

    public Integer getL3mwdcn_TNumsCon()
    {
        return l3mwdcn_TNumsCon;
    }

    public void setL3mwdcn_TNumsCon(Integer l3mwdcn_TNumsCon)
    {
        this.l3mwdcn_TNumsCon = l3mwdcn_TNumsCon;
    }

    public Integer getL3mwdcn_TNumsCon_org()
    {
        return l3mwdcn_TNumsCon_org;
    }

    public void setL3mwdcn_TNumsCon_org(Integer l3mwdcn_TNumsCon_org)
    {
        this.l3mwdcn_TNumsCon_org = l3mwdcn_TNumsCon_org;
    }

    public Integer getL3mwdcn_TNumsCon_bank()
    {
        return l3mwdcn_TNumsCon_bank;
    }

    public void setL3mwdcn_TNumsCon_bank(Integer l3mwdcn_TNumsCon_bank)
    {
        this.l3mwdcn_TNumsCon_bank = l3mwdcn_TNumsCon_bank;
    }

    public Integer getL3mwdcn_TNumsCon_cf()
    {
        return l3mwdcn_TNumsCon_cf;
    }

    public void setL3mwdcn_TNumsCon_cf(Integer l3mwdcn_TNumsCon_cf)
    {
        this.l3mwdcn_TNumsCon_cf = l3mwdcn_TNumsCon_cf;
    }

    public Integer getL3mwdcn_TNumsCon_f()
    {
        return l3mwdcn_TNumsCon_f;
    }

    public void setL3mwdcn_TNumsCon_f(Integer l3mwdcn_TNumsCon_f)
    {
        this.l3mwdcn_TNumsCon_f = l3mwdcn_TNumsCon_f;
    }

    public Integer getL3mwdcn_TNumsCon_if()
    {
        return l3mwdcn_TNumsCon_if;
    }

    public void setL3mwdcn_TNumsCon_if(Integer l3mwdcn_TNumsCon_if)
    {
        this.l3mwdcn_TNumsCon_if = l3mwdcn_TNumsCon_if;
    }
}
