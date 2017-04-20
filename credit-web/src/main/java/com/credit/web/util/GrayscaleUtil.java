package com.credit.web.util;

import java.text.NumberFormat;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.credit.common.context.BeanFactory;
import com.credit.common.exception.ServiceException;
import com.credit.common.util.IDCardUtil;
import com.credit.common.util.MapUtil;
import com.credit.common.util.Validator;
import com.credit.common.util.time.DateUtil;
import com.credit.common.util.time.TimeUtil;
import com.credit.config.OrgTypeCfg;
import com.credit.entity.Applicant;
import com.credit.entity.CallDetails;
import com.credit.entity.NumberTag;
import com.credit.enumeration.*;
import com.credit.service.ScoreService;
import com.credit.service.cache.NumberTagCache;
import com.credit.service.cache.PhoneSegmentInfoCache;
import com.credit.service.impl.ScoreServiceImpl;
import com.credit.web.model.*;

/**
 * Created by wangjunling on 2017/3/10.
 */
public class GrayscaleUtil
{
	private static final Integer TOP_TEN = Integer.valueOf(10);

    private static Logger logger = LoggerFactory.getLogger(GrayscaleUtil.class);


	public static void buildGrayscaleModel(GrayscaleModel grayscaleModel, Applicant applicant) throws Exception
    {
        buildBasicInfo(grayscaleModel.getBasicInfo(), applicant);
        CallDetailsModel callDetailsModel = new CallDetailsModel(applicant.getCallDetailsList(),
                applicant.getApplyDate());
        buildAttributeTag(grayscaleModel.getAttributeTag(), callDetailsModel);
        buildCallDurationStat(grayscaleModel.getCallDurationStat(), callDetailsModel);
        buildContactArea(grayscaleModel.getContactAreaStat(), callDetailsModel);
        buildRiskStatAndContactCheck(grayscaleModel.getRiskStat(), grayscaleModel.getContactCheck(), callDetailsModel);
        buildTopCallOutStat(grayscaleModel.getTopCallOutStat(), callDetailsModel);
        buildTopCallInStat(grayscaleModel.getTopCallInStat(), callDetailsModel);
    }
	public static void buildGrayscaleOrgStat(GrayscaleOrgStat grayscaleOrgStat, Applicant applicant) throws Exception
	{
		buildBasicInfo(grayscaleOrgStat.getBasicInfo(), applicant);
		CallDetailsModel callDetailsModel = new CallDetailsModel(applicant.getCallDetailsList(),
				applicant.getApplyDate());
		buildAttributeTag(grayscaleOrgStat.getAttributeTag(), callDetailsModel);
        buildDunTelTag(grayscaleOrgStat.getDunTelTag(), callDetailsModel);
	}
    public static void buildBasicInfo(BasicInfo basicInfo, Applicant applicant) throws Exception
    {
        if (StringUtils.isBlank(applicant.getIdcard()))
        {
            basicInfo.setIdcard("");
            basicInfo.setEffectiveIdcard("");
            basicInfo.setAge(null);
        }
        else
        {
            basicInfo.setIdcard(applicant.getIdcard());
            basicInfo.setEffectiveIdcard(Validator.isCitizenId(applicant.getIdcard()) ? "有效" : "无效");
            basicInfo.setAge(IDCardUtil.getAgeByIDCard(applicant.getIdcard()));
        }
        basicInfo.setUname(applicant.getAname());
        basicInfo.setPhone(applicant.getPhone());
        PhoneSegmentInfoCache instance = PhoneSegmentInfoCache.getInstance();

        basicInfo.setOperator(instance.getOperator(applicant.getPhone()));
        basicInfo.setPhoneOwnership(instance.getPhoneOwnership(applicant.getPhone()));
    }

    public static void buildTopCallOutStat(List<TopCallOutStat> topCallOutStats, CallDetailsModel callDetailsModel)
            throws Exception
    {
        Map<String, Integer> contactAndTotalTimesMap = callDetailsModel.getContactAndTotalTimesMap();// 包含联系电话号码
                                                                                                     // 及相应的通话总次数
        Map<String, Integer> callOutCountMap = callDetailsModel.getCallOutCount();// 包含联系电话号码
                                                                                  // 及相应的主叫通话总次数
        Map<String, Integer> callOutDurationMap = callDetailsModel.getCallOutDuration();// 包含联系电话号码及相应的主叫通话总时长
        Map<String, Double> contactPeriodicMap = callDetailsModel.getContactPeriodicMap();
        Map<String, Double> contactFrequencyMap = callDetailsModel.getContactFrequencyMap();
        // 根据主被叫次数排序,找出top10
        Map<String, Integer> sortedCallOutCountMapMap = MapUtil.sortByValue(callOutCountMap);
        // 主叫top10
        topCallOutStat(callOutDurationMap, contactAndTotalTimesMap, sortedCallOutCountMapMap, topCallOutStats,
                contactPeriodicMap, contactFrequencyMap);
    }

    public static void buildTopCallInStat(List<TopCallInStat> topCallInStats, CallDetailsModel callDetailsModel)
            throws Exception
    {
        Map<String, Integer> contactAndTotalTimesMap = callDetailsModel.getContactAndTotalTimesMap();// 包含联系电话号码
                                                                                                     // 及相应的通话总次数
        Map<String, Integer> callInCountMap = callDetailsModel.getCallInCount();
        Map<String, Integer> callInDurationMap = callDetailsModel.getCallInDuration();
        Map<String, Double> contactPeriodicMap = callDetailsModel.getContactPeriodicMap();
        Map<String, Double> contactFrequencyMap = callDetailsModel.getContactFrequencyMap();
        // 根据主被叫次数排序,找出top10
        Map<String, Integer> sortedCallInCountMapMap = MapUtil.sortByValue(callInCountMap);
        // 被叫top10
        topCallInStat(callInDurationMap, contactAndTotalTimesMap, sortedCallInCountMapMap, topCallInStats,
                contactPeriodicMap, contactFrequencyMap);
    }

    private static void topCallOutStat(Map<String, Integer> durationMap, Map<String, Integer> totalTimes,
            Map<String, Integer> topCallOut, List<TopCallOutStat> topCallOutStats, Map<String, Double> periodicMap,
            Map<String, Double> contactFrequencyMap) throws Exception
    {
        Integer timesFlag = 1;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(0);
        for (Map.Entry<String, Integer> s : topCallOut.entrySet())
        {
            timesFlag++;
            TopCallOutStat topCallOutStat = new TopCallOutStat();
            topCallOutStat.setNumber(s.getKey());
            topCallOutStat.setCallOutCount(s.getValue());
            topCallOutStat.setCallOutDuration(durationMap.get(s.getKey()));
            topCallOutStat.setCallOutDurationStr(TimeUtil.getTimeInterval(topCallOutStat.getCallOutDuration()));
            double rate = s.getValue() * 1.0 / totalTimes.get(s.getKey());
            topCallOutStat.setCallOutRate(numberFormat.format(rate));
            topCallOutStat.setPeriodicallyContact(PeriodicContactEnum.getPeriodicName(periodicMap.get(s.getKey())));
            topCallOutStat
                    .setCallFrequency(CallFrequencyEnum.getCallFrequencyName(contactFrequencyMap.get(s.getKey())));
            topCallOutStats.add(topCallOutStat);
            if (timesFlag > TOP_TEN)
            {
                break;
            }
        }
    }

    private static void topCallInStat(Map<String, Integer> durationMap, Map<String, Integer> totalTimes,
            Map<String, Integer> topCallInData, List<TopCallInStat> topCallInStats, Map<String, Double> periodicMap,
            Map<String, Double> contactFrequencyMap) throws Exception
    {
        Integer timesFlag = 1;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(0);
        for (Map.Entry<String, Integer> s : topCallInData.entrySet())
        {
            timesFlag++;
            TopCallInStat topCallInStat = new TopCallInStat();
            topCallInStat.setNumber(s.getKey());
            topCallInStat.setCallInCount(s.getValue());
            topCallInStat.setCallInDuration(durationMap.get(s.getKey()));
			topCallInStat.setCallInDurationStr(TimeUtil.getTimeInterval(topCallInStat.getCallInDuration()));
            double rate = s.getValue() * 1.0 / totalTimes.get(s.getKey());
            topCallInStat.setCallInRate(numberFormat.format(rate));
            topCallInStat.setPeriodicallyContact(PeriodicContactEnum.getPeriodicName(periodicMap.get(s.getKey())));
            topCallInStat.setCallFrequency(CallFrequencyEnum.getCallFrequencyName(contactFrequencyMap.get(s.getKey())));
            topCallInStats.add(topCallInStat);
            if (timesFlag > TOP_TEN)
            {
                break;
            }
        }
    }

    private static void buildAttributeTag(AttributeTag attributeTag, CallDetailsModel callDetailsModel)
            throws Exception
    {
        int monthSize = callDetailsModel.getMonthSet().size();
        // 设置联系人总数
        attributeTag.setContactCount(callDetailsModel.getContactMap().size());
        int workDayTime = callDetailsModel.getWorkingDayCallDetailsList().size();
        int restDayTime = callDetailsModel.getWeekendCallDetailsList().size();

        // 设置通话频率最高的时间段(休息日，工作日)
        if (workDayTime >= restDayTime)
        {
            attributeTag.setMaxCallTimeSlot(WorkEnum.WORK_DAY.getText());
        }
        else
        {
            attributeTag.setMaxCallTimeSlot(WorkEnum.REST_DAY.getText());
        }
        if (monthSize > 0)
        {
            Integer contactCount = callDetailsModel.getContactMap().size();
            Integer callCount = callDetailsModel.getCallDetailsList().size();
            attributeTag.setMonthAvgContactCount(contactCount / monthSize);
            attributeTag.setMonthAvgCallCount(callCount / monthSize);
            attributeTag.setActive(ActiveEnum.getActive(contactCount / monthSize * 1.0));
            attributeTag.setStability(StabilityEnum.getStability(callCount / monthSize * 1.0));
        }
        List<Long> callTimeList = callDetailsModel.getCallTimeList();
        if (callTimeList.size() > 1)
        {
            // 升序
            Collections.sort(callTimeList);
            long[] intervals = new long[callTimeList.size() - 1];
            int index = 0;
            int intervalCount = 0;
            // 获取是呼叫时间的时间间隔 单位：秒
            for (int i = callTimeList.size() - 1; i > 0; i--)
            {
                long interval = (callTimeList.get(i) - callTimeList.get(i - 1)) / 1000;
                intervals[index++] = interval;
                intervalCount += interval;
            }
            Arrays.sort(intervals);
            attributeTag.setMinCallTimeInterval((int)intervals[0]);
            attributeTag.setMaxCallTimeInterval((int) intervals[intervals.length - 1]);
            attributeTag.setAvgCallTimeInterval(intervalCount / intervals.length);
            attributeTag.setMinCallTimeIntervalStr(TimeUtil.getTimeInterval(attributeTag.getMinCallTimeInterval()));
            attributeTag.setMaxCallTimeIntervalStr(TimeUtil.getTimeInterval(attributeTag.getMaxCallTimeInterval()));
            attributeTag.setAvgCallTimeIntervalStr(TimeUtil.getTimeInterval(attributeTag.getAvgCallTimeInterval()));
        }
        attributeTag.setCallOutFirstTierCitiesCount(callDetailsModel.getCallOutFirstTierCitiesCount());
        attributeTag.setAvgDuration(callDetailsModel.getTotalDuration() / callDetailsModel.getCallDetailsList().size());
        attributeTag.setAvgDurationStr(TimeUtil.getTimeInterval(attributeTag.getAvgDuration()));
        attributeTag.setFriendsCircle(callDetailsModel.getMaxContactAddr());
        attributeTag.setPeriodicContactCount(callDetailsModel.getPeriodicContactCount());
		Integer score = calculateScore(callDetailsModel);
		attributeTag.setGrayscaleScore(score);
	}

    public static void buildContactArea(List<ContactAreaStat> contactAreaList, CallDetailsModel callDetailsModel)
            throws Exception
    {

        Map<String, Map<String, List<CallDetails>>> contactAddrMap = callDetailsModel.getContactAddrMap();
        for (Map.Entry<String, Map<String, List<CallDetails>>> stringMapEntry : contactAddrMap.entrySet())
        {
            if (StringUtils.isBlank(stringMapEntry.getKey()))
            {
                continue;
            }
            ContactAreaStat contactArea = new ContactAreaStat();
            contactArea.setArea(stringMapEntry.getKey());
            Map<String, List<CallDetails>> contactMap = stringMapEntry.getValue();
            contactArea.setNumberCount(contactMap.size());
            int callOutCount = 0;
            int callInCount = 0;
            int callOutDuration = 0;
            int callInDuration = 0;
            for (Map.Entry<String, List<CallDetails>> contactEntry : contactMap.entrySet())
            {
                List<CallDetails> value = contactEntry.getValue();
                for (CallDetails details : value)
                {
                    if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
                    {
                        callOutDuration += details.getDuration();
                        callOutCount++;
                    }
                    else
                        if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                        {
                            callInDuration += details.getDuration();
                            callInCount++;
                        }
                }
            }
            contactArea.setCallOutCount(callOutCount);
            contactArea.setCallInCount(callInCount);
            contactArea.setCallOutDuration(callOutDuration);
            contactArea.setCallOutDurationStr(TimeUtil.getTimeInterval(callOutDuration));
            contactArea.setCallInDuration(callInDuration);
            contactArea.setCallInDurationStr(TimeUtil.getTimeInterval(callInDuration));
            int callCount = callOutCount + callInCount;
            double callOutRate = callOutCount * 1.0 / callCount;
            double callInRate = callInCount * 1.0 / callCount;
            contactArea.setCallOutRatio((int) (callOutRate * 100) + "%");
            contactArea.setCallInRatio((int) (callInRate * 100) + "%");
            contactAreaList.add(contactArea);
        }
    }

    public static void buildRiskStatAndContactCheck(List<RiskStat> riskStatList, List<ContactCheck> contactCheckList,
            CallDetailsModel callDetailsModel) throws Exception
    {
        Map<String, List<CallDetails>> contactMap = callDetailsModel.getContactMap();
        Map<String, Double> contactPeriodicMap = callDetailsModel.getContactPeriodicMap();
        Map<String, Double> contactFrequencyMap = callDetailsModel.getContactFrequencyMap();
        for (Map.Entry<String, List<CallDetails>> contactCall : contactMap.entrySet())
        {
            String contact = contactCall.getKey();
            RiskStat riskStat = new RiskStat();
            riskStat.setNumber(contact);
            NumberTag numberTag = NumberTagCache.getInstance().getNumberTagCacheData(contact);
            riskStat.setOrgType("普通联系人");
            riskStat.setDemandType("");
            if (null != numberTag)
            {
                if (numberTag.getDemandType() != null)
                {
                    riskStat.setDemandType(numberTag.getDemandType().getDemandTypeName());
                    riskStat.setDemandClass(numberTag.getDemandClass());
                }
                if (numberTag.getOrgType() != null)
                {
                    riskStat.setOrgType(numberTag.getOrgType().getOrgTypeName());
                    riskStat.setOrgClass(numberTag.getOrgClass());
                }
                riskStat.setLastMarkDate(numberTag.getMaxDate());
            }
            List<CallDetails> callDetailsList = contactCall.getValue();
            int totalDuration = 0;
            int callOutCount = 0;
            int callInCount = 0;
            for (CallDetails details : callDetailsList)
            {
                totalDuration += details.getDuration();
                if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
                {
                    callOutCount++;
                }
                else
                    if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                    {
                        callInCount++;
                    }
            }
            riskStat.setCallDuration(totalDuration);
            riskStat.setCallDurationStr(TimeUtil.getTimeInterval(totalDuration));
            riskStat.setCallCount(callDetailsList.size());
            riskStat.setCallOutCount(callOutCount);
            riskStat.setCallInCount(callInCount);
            riskStat.setOwnership(callDetailsList.get(0).getContactAddr());
            String periodicName = PeriodicContactEnum.getPeriodicName(contactPeriodicMap.get(contact));
            Double freNum = contactFrequencyMap.get(contact);
            String callFrequencyName = CallFrequencyEnum.getCallFrequencyName(freNum);
            riskStat.setPeriodicallyContact(periodicName);
            riskStat.setCallFrequency(callFrequencyName);
            riskStat.setCallFrequencyNum(freNum == null ? 0 : freNum);
            riskStatList.add(riskStat);
            if (CollectionUtils.isEmpty(contactCheckList))
            {
                continue;
            }
            for (ContactCheck contactCheck : contactCheckList)
            {
                if (contactCheck.getNumber().equals(contact))
                {
                    contactCheck.setCallCount(callDetailsList.size());
                    contactCheck.setCallInCount(callInCount);
                    contactCheck.setCallOutCount(callOutCount);
                    contactCheck.setCallFrequency(callFrequencyName);
                    contactCheck.setPeriodicallyContact(periodicName);
                }
            }
        }
        Collections.sort(riskStatList, new Comparator<RiskStat>()
        {
            @Override
            public int compare(RiskStat o1, RiskStat o2)
            {
                return o2.getCallFrequencyNum().compareTo(o1.getCallFrequencyNum());
            }
        });
    }

    public static void buildCallDurationStat(CallDurationStat callDurationStat, CallDetailsModel callDetailsModel)
            throws Exception
    {
        CallDurationModel workingDay = new CallDurationModel();
        CallDurationModel weekend = new CallDurationModel();
        List<CallDetails> workingDayCallDetailsList = callDetailsModel.getWorkingDayCallDetailsList();
        List<CallDetails> weekendCallDetailsList = callDetailsModel.getWeekendCallDetailsList();
        calculateCallDuration(workingDay, workingDayCallDetailsList);
        calculateCallDuration(weekend, weekendCallDetailsList);
        callDurationStat.setWorkingDay(workingDay);
        callDurationStat.setWeekend(weekend);
    }

    private static void calculateCallDuration(CallDurationModel callDurationModel, List<CallDetails> callDetailsList)
            throws Exception
    {
        int callOutCount = 0;
        int callInCount = 0;
        int callOutDuration = 0;
        int callInDuration = 0;
        Set<String> contactSet = new HashSet<>();
        for (CallDetails details : callDetailsList)
        {
            contactSet.add(details.getContact());
            if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
            {
                callOutDuration += details.getDuration();
                callOutCount++;
            }
            else
                if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                {
                    callInDuration += details.getDuration();
                    callInCount++;
                }
        }
        callDurationModel.setNumberCount(contactSet.size());
        callDurationModel.setCallOutCount(callOutCount);
        callDurationModel.setCallInCount(callInCount);
        callDurationModel.setCallOutDuration(callOutDuration);
        callDurationModel.setCallOutDurationStr(TimeUtil.getTimeInterval(callOutDuration));
        callDurationModel.setCallInDuration(callInDuration);
        callDurationModel.setCallInDurationStr(TimeUtil.getTimeInterval(callInDuration));
        int callCount = callOutCount + callInCount;
        double callOutRate = callOutCount * 1.0 / callCount;
        double callInRate = callInCount * 1.0 / callCount;
        callDurationModel.setCallOutRatio((int) (callOutRate * 100) + "%");
        callDurationModel.setCallInRatio((int) (callInRate * 100) + "%");
    }

    private static Integer calculateScore(CallDetailsModel callDetailsModel)
    {

        Map<String, List<CallDetails>> contactMap = callDetailsModel.getContactMap();

        Date applyDate = callDetailsModel.getApplyDate();
        Date last2Mouth = DateUtil.addMonth(applyDate, -2);
        Date last3Mouth = DateUtil.addMonth(applyDate, -3);
        Date last4Mouth = DateUtil.addMonth(applyDate, -4);
        Date last5Mouth = DateUtil.addMonth(applyDate, -5);

        // last4month（近四月） wholeday（全天） collection_number（全部催收号）
        // 被叫次数最大的单个催收号的总时长
        int l4mMaxCallInCount = 0;
        int l4mMaxCallInCountTotalDuration = 0;
        // last4month（近四月） wholeday（全天） collection_number（全部催收号） 被叫催收号的机构个数
        Set<Integer> l4mOrgTypeSet = new HashSet<>();

        // last5month（近五月） wholeday（全天） collection_number（全部催收号） 被叫催收公司总时长
        int l5mTotalCallInDuration = 0;

        // last3month（近三月） wholeday（全天） collection_number（全部催收号） 被叫催收号总次数
        int l3mTotalCallInCount = 0;

        // last4month（近四月） wholeday（全天） collection_number（全部催收号） 联系催收公司的总个数
        int l4TotalCount = 0;

        // last2month（近两月） wholeday（全天） collection_number（全部催收号） 被叫单个催收号的最大次数
        int l2mMaxCallInCount = 0;

        // 整体 wholeday（全天） collection_mobile（催收手机） 被叫次数最大的单个催收号机构类型
        int maxCallInCount = 0;
        Integer maxCallInCountOrgType = 0;

        // last5month（近五月） wholeday（全天） collection_number（全部催收号） 联系互联网金融机构的总个数
        Set<String> l5mFinancialOrg = new HashSet<>();

        // last2month（近两月） wholeday（全天） all_contact_number(所有号码，用于详单变量) 被叫通话记录数
        int l2mCallInTotalNotDunCount = 0;

        // last3month（近三月） evening（晚上） collection_number（全部催收号） 联系催收号的机构个数
        int l3menOrgCount = 0;
        for (Map.Entry<String, List<CallDetails>> entry : contactMap.entrySet())
        {
            String number = entry.getKey();
            List<CallDetails> callDetailss = entry.getValue();
            NumberTag numberTag = null;
            try
            {
                numberTag = NumberTagCache.getInstance().getNumberTagCacheData(number);
            }
            catch (ServiceException e)
            {
                e.printStackTrace();
            }
            int l4mCallInCount = 0;
            int l4mCallInCountDuration = 0;
            int l2mCallInCount = 0;
            int callInCountMobile = 0;
            Integer orgType = null;

            List<Integer> orgTypeIds = OrgTypeCfg.getInternetFinanceIds();

            for (CallDetails details : callDetailss)
            {
                if (numberTag != null && numberTag.isDun())
                {
                    Date callTime = TimeUtil.parse(details.getCallTime(), TimeUtil.FORMAT_NORMAL);
                    if (callTime != null && callTime.getTime() > last3Mouth.getTime() && TimeUtil.isEvening(callTime))
                    {
                        l3menOrgCount++;
                    }
                    if (callTime != null && callTime.getTime() > last4Mouth.getTime() && numberTag.getOrgTypeId().equals(34))
                    {
                        l4TotalCount++;
                    }

                    if (callTime != null && callTime.getTime() > last5Mouth.getTime() && orgTypeIds != null
                            && orgTypeIds.contains(numberTag.getOrgTypeId()))// 金融机构
                    {
                        l5mFinancialOrg.add(number);
                    }
                    if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                    {
                        if (Validator.isMobile(number))
                        {
                            callInCountMobile++;
                            orgType = numberTag.getOrgTypeId();
                        }

                        if (callTime != null && callTime.getTime() > last2Mouth.getTime())
                        {
                            l2mCallInCount++;
                        }
                        if (callTime != null && callTime.getTime() > last3Mouth.getTime())
                        {
                            l3mTotalCallInCount++;
                        }
                        if (callTime != null && callTime.getTime() > last4Mouth.getTime())
                        {
                            l4mCallInCount++;
                            l4mCallInCountDuration += details.getDuration();
                            Integer orgTypeId = numberTag.getOrgTypeId();
                            if (orgTypeId != 0)
                            {
                                l4mOrgTypeSet.add(orgTypeId);
                            }
                        }
                        if (callTime != null && callTime.getTime() > last5Mouth.getTime()
                                && numberTag.getOrgTypeId().equals(34))
                        {
                            l5mTotalCallInDuration += details.getDuration();
                        }

                    }

                    if (l4mCallInCount > l4mMaxCallInCount)
                    {
                        l4mMaxCallInCount = l4mCallInCount;
                        l4mMaxCallInCountTotalDuration = l4mCallInCountDuration;
                    }
                    if (l2mCallInCount > l2mMaxCallInCount)
                    {
                        l2mMaxCallInCount = l2mCallInCount;
                    }
                    if (callInCountMobile > maxCallInCount)
                    {
                        maxCallInCount = callInCountMobile;
                        maxCallInCountOrgType = orgType;
                    }
                }
                if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                {
                    l2mCallInTotalNotDunCount++;
                }
            }
        }
		Map<String, Integer> varNameValue = new HashMap<>();
		varNameValue.put(ScoreEnum.L4MWDCN__T_DUR_IN_MAXTIMES.getText(), l4mMaxCallInCountTotalDuration);
		varNameValue.put(ScoreEnum.L4MWDCN__T_NUMS_IN_ORG.getText(), l4mOrgTypeSet.size());
		varNameValue.put(ScoreEnum.L5MWDCN__T_DUR_IN_F.getText(), l5mTotalCallInDuration);
		varNameValue.put(ScoreEnum.L3MWDCN__T_TIMES_IN.getText(), l3mTotalCallInCount);
		varNameValue.put(ScoreEnum.L4MWDCN__T_NUMS_CON_F.getText(), l4TotalCount);
		varNameValue.put(ScoreEnum.L2MWDCN__MAX_TIMES_IN.getText(), l2mMaxCallInCount);
		varNameValue.put(ScoreEnum.ALLWDCM__MAX_ORGTYPE_IN.getText(), maxCallInCountOrgType);
		varNameValue.put(ScoreEnum.L2MWDAC__DTL_T_TIMES_IN.getText(), l2mCallInTotalNotDunCount);
		varNameValue.put(ScoreEnum.L5MWDCN__T_NUMS_CON_IF.getText(), l5mFinancialOrg.size());
		varNameValue.put(ScoreEnum.L3MENCN__T_NUMS_CON_ORG.getText(), l3menOrgCount);
		try
		{
			ScoreService scoreService = BeanFactory.getBean("scoreService", ScoreServiceImpl.class);
			return scoreService.search(varNameValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
    }

    public static void statGrayscale(Applicant applicant, GrayscaleStat stat)
    {
        CallDetailsModel callDetailsModel = new CallDetailsModel(applicant.getCallDetailsList(),
                applicant.getApplyDate());
        Map<String, List<CallDetails>> contactMap = callDetailsModel.getContactMap();
        stat.setNumsCon(contactMap.size());
        Date applyDate = callDetailsModel.getApplyDate();
        Date last3Mouth = DateUtil.addMonth(applyDate, -3);
        Date last1Mouth = DateUtil.addMonth(applyDate, -1);
        Date last1Week = DateUtil.lowerDay(applyDate, 7);

        Set<String> l3mBankOrgCodeSet = new HashSet<>();
        Set<String> l3mCfOrgCodeSet = new HashSet<>();
        Set<String> l3mFOrgCodeSet = new HashSet<>();
        Set<String> l3mIfOrgCodeSet = new HashSet<>();
        Set<String> l3mDunNumberSet = new HashSet<>();
        Set<String> l3mOrgTypeSet = new HashSet<>();

        Set<String> l1mBankOrgCodeSet = new HashSet<>();
        Set<String> l1mCfOrgCodeSet = new HashSet<>();
        Set<String> l1mFOrgCodeSet = new HashSet<>();
        Set<String> l1mIfOrgCodeSet = new HashSet<>();
        Set<String> l1mDunNumberSet = new HashSet<>();
        Set<String> l1mOrgTypeSet = new HashSet<>();

        Set<String> allBankOrgCodeSet = new HashSet<>();
        Set<String> allCfOrgCodeSet = new HashSet<>();
        Set<String> allFOrgCodeSet = new HashSet<>();
        Set<String> allIfOrgCodeSet = new HashSet<>();
        Set<String> allDunNumberSet = new HashSet<>();
        Set<String> allOrgTypeSet = new HashSet<>();

        Set<String> l1wBankOrgCodeSet = new HashSet<>();
        Set<String> l1wCfOrgCodeSet = new HashSet<>();
        Set<String> l1wFOrgCodeSet = new HashSet<>();
        Set<String> l1wIfOrgCodeSet = new HashSet<>();
        Set<String> l1wDunNumberSet = new HashSet<>();
        Set<String> l1wOrgTypeSet = new HashSet<>();

        List<Integer> internetFinanceIds = OrgTypeCfg.getInternetFinanceIds();

        for (Map.Entry<String, List<CallDetails>> entry : contactMap.entrySet())
        {
            String number = entry.getKey();
            NumberTag numberTag = null;
            try
            {
                numberTag = NumberTagCache.getInstance().getNumberTagCacheData(number);
            }
            catch (ServiceException e)
            {
                logger.error("从缓存中获取号码标签异常", e);
            }
            if (numberTag == null)
            {
                continue;
            }

            List<CallDetails> list = entry.getValue();
            for (CallDetails details : list)
            {
                if (numberTag.isDun())
                {
					System.out.println(details.getContact());
					allDunNumberSet.add(numberTag.getNumber());
                    allOrgTypeSet.add(numberTag.getOrgCode());
                    if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
                    {
                        stat.setAllwdcn_TTimesOut(stat.getAllwdcn_TTimesOut() + 1);
                    }
                    else
                        if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                        {
                            stat.setAllwdcn_TTimesIn(stat.getAllwdcn_TTimesIn() + 1);
                        }
                    statOrgCodeCount(numberTag, allBankOrgCodeSet, allCfOrgCodeSet, allFOrgCodeSet, allIfOrgCodeSet,
                            internetFinanceIds);
                }

                Date callTime = TimeUtil.parse(details.getCallTime(), TimeUtil.FORMAT_NORMAL);
                if (callTime != null && callTime.getTime() > last3Mouth.getTime())
                {
                    if (numberTag.isDun())
                    {
                        l3mDunNumberSet.add(numberTag.getNumber());
                        l3mOrgTypeSet.add(numberTag.getOrgCode());
                        if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
                        {
                            stat.setL3mwdcn_TTimesOut(stat.getL3mwdcn_TTimesOut() + 1);
                        }
                        else
                            if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                            {
                                stat.setL3mwdcn_TTimesIn(stat.getL3mwdcn_TTimesIn() + 1);
                            }
                        statOrgCodeCount(numberTag, l3mBankOrgCodeSet, l3mCfOrgCodeSet, l3mFOrgCodeSet,
                                l3mIfOrgCodeSet, internetFinanceIds);
                    }
                }
                if (callTime != null && callTime.getTime() > last1Mouth.getTime())
                {
                    if (numberTag.isDun())
                    {
                        l1mDunNumberSet.add(numberTag.getNumber());
                        l1mOrgTypeSet.add(numberTag.getOrgCode());
                        if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
                        {
                            stat.setL1mwdcn_TTimesOut(stat.getL1mwdcn_TTimesOut() + 1);
                        }
                        else
                            if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                            {
                                stat.setL1mwdcn_TTimesIn(stat.getL1mwdcn_TTimesIn() + 1);
                            }
                        statOrgCodeCount(numberTag, l1mBankOrgCodeSet, l1mCfOrgCodeSet, l1mFOrgCodeSet,
                                l1mIfOrgCodeSet, internetFinanceIds);
                    }

                }
                if (callTime != null && callTime.getTime() > last1Week.getTime())
                {
                    if (numberTag.isDun())
                    {
                        l1wDunNumberSet.add(numberTag.getNumber());
                        l1wOrgTypeSet.add(numberTag.getOrgCode());
                        if (CallModelEnum.CALL_OUT.getComment().equals(details.getFormatCallModel()))
                        {
                            stat.setL1wwdcn_TTimesOut(stat.getL1wwdcn_TTimesOut() + 1);
                        }
                        else
                            if (CallModelEnum.CALL_IN.getComment().equals(details.getFormatCallModel()))
                            {
                                stat.setL1wwdcn_TTimesIn(stat.getL1wwdcn_TTimesIn() + 1);
                            }
                        statOrgCodeCount(numberTag, l1wBankOrgCodeSet, l1wCfOrgCodeSet, l1wFOrgCodeSet,
                                l1wIfOrgCodeSet, internetFinanceIds);
                    }
                }
            }
        }
        stat.setL3mwdcn_TNumsCon_bank(l3mBankOrgCodeSet.size());
        stat.setL3mwdcn_TNumsCon_cf(l3mCfOrgCodeSet.size());
        stat.setL3mwdcn_TNumsCon_f(l3mFOrgCodeSet.size());
        stat.setL3mwdcn_TNumsCon_if(l3mIfOrgCodeSet.size());
        stat.setL3mwdcn_TNumsCon(l3mDunNumberSet.size());
        stat.setL3mwdcn_TNumsCon_org(l3mOrgTypeSet.size());

        stat.setAllwdcn_TNumsCon_bank(allBankOrgCodeSet.size());
        stat.setAllwdcn_TNumsCon_cf(allCfOrgCodeSet.size());
        stat.setAllwdcn_TNumsCon_f(allFOrgCodeSet.size());
        stat.setAllwdcn_TNumsCon_if(allIfOrgCodeSet.size());
        stat.setAllwdcn_TNumsCon(allDunNumberSet.size());
        stat.setAllwdcn_TNumsCon_org(allOrgTypeSet.size());

        stat.setL1mwdcn_TNumsCon_bank(l1mBankOrgCodeSet.size());
        stat.setL1mwdcn_TNumsCon_cf(l1mCfOrgCodeSet.size());
        stat.setL1mwdcn_TNumsCon_f(l1mFOrgCodeSet.size());
        stat.setL1mwdcn_TNumsCon_if(l1mIfOrgCodeSet.size());
        stat.setL1mwdcn_TNumsCon(l1mDunNumberSet.size());
        stat.setL1mwdcn_TNumsCon_org(l1mOrgTypeSet.size());

        stat.setL1wwdcn_TNumsCon_bank(l1wBankOrgCodeSet.size());
        stat.setL1wwdcn_TNumsCon_cf(l1wCfOrgCodeSet.size());
        stat.setL1wwdcn_TNumsCon_f(l1wFOrgCodeSet.size());
        stat.setL1wwdcn_TNumsCon_if(l1wIfOrgCodeSet.size());
        stat.setL1wwdcn_TNumsCon(l1wDunNumberSet.size());
        stat.setL1wwdcn_TNumsCon_org(l1wOrgTypeSet.size());
    }

    private static void statOrgCodeCount(NumberTag numberTag, Set<String> bankOrgCodeSet, Set<String> cfOrgCodeSet,
            Set<String> fOrgCodeSet, Set<String> ifOrgCodeSet, List<Integer> internetFinanceIds)
    {
        if (numberTag.getOrgTypeId() == 50)// 银行
        {
            bankOrgCodeSet.add(numberTag.getOrgCode());
        }
        else
            if (numberTag.getOrgTypeId() == 52)// 消费金融
            {
                cfOrgCodeSet.add(numberTag.getOrgCode());

            }
            else
                if (numberTag.getOrgTypeId() == 34)// 催收
                {
                    fOrgCodeSet.add(numberTag.getOrgCode());
                }
                else
                    if (internetFinanceIds.contains(numberTag.getOrgTypeId()))// 互联网金融
                    {
                        ifOrgCodeSet.add(numberTag.getOrgCode());
                    }

    }

    public static void buildDunTelTag(DunTelTag dunTelTag, CallDetailsModel callDetailsModel) throws Exception
    {
        Map<String, List<CallDetails>> contactMap = callDetailsModel.getContactMap();

        Set<String> bankOrgCodeSet = new HashSet<String>();
        Set<String> fOrgCodeSet    = new HashSet<String>();
        Set<String> ifOrgCodeSet   = new HashSet<String>();
        int dunTelCount      = 0;
        int bankDunTelCount  = 0;
        int iFDunTelCount    = 0;
        int otherDunTelCount = 0;
        int dunTelLikeCount  = 0;

        List<Integer> internetFinanceIds = OrgTypeCfg.getInternetFinanceIds();
        if(null != contactMap
                && !contactMap.isEmpty())
        {
            for (Map.Entry<String, List<CallDetails>> contactCall : contactMap.entrySet())
            {
                List<CallDetails> callDetails = contactCall.getValue();

                if(null != callDetails
                        && !callDetails.isEmpty())
                {
                    for(CallDetails callDetail : callDetails)
                    {
                        NumberTag numberTag = NumberTagCache.getInstance().getNumberTagCacheData(callDetail.getContact());
                        if (null != numberTag
                                && numberTag.isDun())
                        {
                            if(StringUtils.isBlank(numberTag.getOrgName())
                                    || null == numberTag.getOrgTypeId())
                            {
                                dunTelLikeCount += 1;
                            }
                            else
                            {
                                dunTelCount += 1;
                                if (numberTag.getOrgTypeId() == 50)// 银行
                                {
                                    bankDunTelCount += 1;
                                    bankOrgCodeSet.add(numberTag.getOrgName());
                                }
                                else if (numberTag.getOrgTypeId() == 34)// 委外催收
                                {
                                    otherDunTelCount += 1;
                                    fOrgCodeSet.add(numberTag.getOrgName());
                                }
                                else if (internetFinanceIds.contains(numberTag.getOrgTypeId()))// 互联网金融
                                {
                                    iFDunTelCount += 1;
                                    ifOrgCodeSet.add(numberTag.getOrgName());
                                }
                            }
                        }
                    }
                }
            }
        }

        dunTelTag.setBankDunTelCount(bankDunTelCount);
        dunTelTag.setiFDunTelCount(iFDunTelCount);
        dunTelTag.setOtherDunTelCount(otherDunTelCount);
        dunTelTag.setDunTelCount(dunTelCount);
        dunTelTag.setDunTelLikeCount(dunTelLikeCount);
        dunTelTag.setBankDunTelOrgs(bankOrgCodeSet.isEmpty() ? "" : StringUtils.join(bankOrgCodeSet, " "));
        dunTelTag.setiFDunTelOrgs(ifOrgCodeSet.isEmpty() ? "" : StringUtils.join(ifOrgCodeSet, " "));
        dunTelTag.setOtherDunTelOrgs(fOrgCodeSet.isEmpty() ? "" : StringUtils.join(fOrgCodeSet, " "));
    }

    public static void main(String[] args)
    {
        // String
        // detailJson="[ { \"duration\":\"16\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"call_model\":\"被叫\", \"call_addr\":\"上海\" }, { \"duration\":\"153\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"call_model\":\"主叫\", \"call_addr\":\"上海\" } ]";
        // String
        // detailJson="[ { \"duration\":\"16\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"call_model\":\"被叫\", \"call_addr\":\"上海\" }, { \"duration\":\"153\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"call_model\":\"主叫\", \"call_addr\":\"上海\" }, { \"duration\":\"64\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"call_model\":\"主叫\", \"call_addr\":\"上海\" } , { \"duration\":\"87\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"call_model\":\"被叫\", \"call_addr\":\"上海\" },{ \"duration\":\"16\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"call_model\":\"主叫\", \"call_addr\":\"上海\" },{ \"duration\":\"35\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"call_model\":\"主叫\", \"call_addr\":\"上海\" } ,{ \"duration\":\"35\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"call_model\":\"主叫\", \"call_addr\":\"上海\" } ]";
        String detailJson = "[{ \"duration\":\"16\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"被叫\", \"call_addr\":\"上海\" }, { \"duration\":\"153\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" },\n"
                + "{ \"duration\":\"356\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"format_call_model\":\"被叫\", \"call_addr\":\"上海\" },\n"
                + "{ \"duration\":\"98\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" },\n"
                + "{ \"duration\":\"153\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-30 05:29:47\", \"format_call_model\":\"被叫\", \"call_addr\":\"上海\" },\n"
                + "{ \"duration\":\"86\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" },{ \"duration\":\"86\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"11224875\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" },{ \"duration\":\"86\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"98765412\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" },{ \"duration\":\"86\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"145875645\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" },{ \"duration\":\"86\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" }]";
        // String
        // detailJson="[ { \"duration\":\"16\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"18575807939\", \"call_time\":\"2016-11-29 18:23:53\", \"format_call_model\":\"被叫\", \"call_addr\":\"上海\" }, { \"duration\":\"153\", \"contact_addr\":\"上海\", \"called_type\":\"国内通话\", \"cost\":\"20\", \"contact\":\"4008208788\", \"call_time\":\"2016-11-30 05:29:47\", \"format_call_model\":\"主叫\", \"call_addr\":\"上海\" } ]";
        List<CallDetails> callDetailss = JSONArray.parseArray(detailJson, CallDetails.class);
        AttributeTag tag = new AttributeTag();
        GrayscaleModel grayscaleModel = new GrayscaleModel();

        try
        {
            // buildAttributeTag(tag,new CallDetailsModel(callDetailss));
            // System.out.println(JSON.toJSONString(tag));

            CallDetailsModel callDetailsModel = new CallDetailsModel(callDetailss, null);
            buildTopCallOutStat(grayscaleModel.getTopCallOutStat(), callDetailsModel);
            // List<TopCallInStat>
            // callInStats=buildTopCallInStat(callDetailsModel);
            // System.out.println(JSON.toJSON(mainContactTopTens));
            System.out.println(JSON.toJSON(grayscaleModel.getTopCallOutStat()));
            /*
             * buildAttributeTag(tag,callDetailsModel);
             * System.out.println(JSON.toJSONString(tag)); List<ContactAreaStat>
             * areaList = new ArrayList<>();
             * buildContactArea(areaList,callDetailsModel);
             * System.out.println(JSON.toJSONString(areaList)); List<RiskStat>
             * riskStatList = new ArrayList<>();
             * buildRiskStatAndContactCheck(riskStatList,callDetailsModel);
             * System.out.println(JSON.toJSONString(riskStatList));
             * CallDurationStat callDurationStat = new CallDurationStat();
             * buildCallDurationStat(callDurationStat,callDetailsModel);
             * System.out.println(JSON.toJSONString(callDurationStat));
             */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
