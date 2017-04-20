package com.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.credit.entity.PhoneSegmentInfo;
import com.credit.repository.PhoneSegmentInfoRepository;
import com.credit.service.PhoneSegmentInfoService;

/**
 * Created by wangjunling on 2017/3/14.
 */
@Service("phoneSegmentInfoService")
public class PhoneSegmentInfoServiceImpl implements PhoneSegmentInfoService
{
    @Resource
    private PhoneSegmentInfoRepository phoneSegmentInfoRepository;

    @Override
    public PhoneSegmentInfo searchByPhone(String phoneNumber)
    {
        if (StringUtils.isBlank(phoneNumber))
        {
            return null;
        }
        return phoneSegmentInfoRepository.findByPhoneSegment(phoneNumber.substring(0, 7));
    }

    @Override
    public List<PhoneSegmentInfo> searchAll()
    {
        return phoneSegmentInfoRepository.findAll();
    }
}
