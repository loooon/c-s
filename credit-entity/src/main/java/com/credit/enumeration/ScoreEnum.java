package com.credit.enumeration;

/**
 * Created by wangjunling on 2017/3/27.
 */
public enum ScoreEnum
{
    /**
     * last4month（近四月） wholeday（全天） collection_number（全部催收号） 被叫次数最大的单个催收号的总时长
     */
    L4MWDCN__T_DUR_IN_MAXTIMES("l4mwdcn_TDurIn_maxtimes"),

    /**
     * last4month（近四月） wholeday（全天） collection_number（全部催收号） 被叫催收号的机构个数
     */
    L4MWDCN__T_NUMS_IN_ORG("l4mwdcn_TNumsIn_org"),

    /**
     * last5month（近五月） wholeday（全天） collection_number（全部催收号） 被叫催收公司总时长
     */
    L5MWDCN__T_DUR_IN_F("l5mwdcn_TDurIn_f"),

    /**
     * last3month（近三月） wholeday（全天） collection_number（全部催收号） 被叫催收号总次数
     */
    L3MWDCN__T_TIMES_IN("l3mwdcn_TTimesIn"),

    /**
     * last4month（近四月） wholeday（全天） collection_number（全部催收号） 联系催收公司的总个数
     */
    L4MWDCN__T_NUMS_CON_F("l4mwdcn_TNumsCon_f"),

    /**
     * last2month（近两月） wholeday（全天） collection_number（全部催收号） 被叫单个催收号的最大次数
     */
    L2MWDCN__MAX_TIMES_IN("l2mwdcn_MaxTimesIn"),

    /**
     * 整体 wholeday（全天） collection_mobile（催收手机） 被叫次数最大的单个催收号机构类型
     */
    ALLWDCM__MAX_ORGTYPE_IN("allwdcm_MaxOrgtypeIn"),

    /**
     * last2month（近两月） wholeday（全天） all_contact_number(所有号码，用于详单变量) 被叫通话记录数
     */
    L2MWDAC__DTL_T_TIMES_IN("l2mwdac_DtlTTimesIn"),

    /**
     * last5month（近五月） wholeday（全天） collection_number（全部催收号） 联系互联网金融机构的总个数
     */
    L5MWDCN__T_NUMS_CON_IF("l5mwdcn_TNumsCon_if"),

    /**
     * last3month（近三月） evening（晚上） collection_number（全部催收号） 联系催收号的机构个数
     */
    L3MENCN__T_NUMS_CON_ORG("l3mencn_TNumsCon_org");

    private String text;

    ScoreEnum(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}
