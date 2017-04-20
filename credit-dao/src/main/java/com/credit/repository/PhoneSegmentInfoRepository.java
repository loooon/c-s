package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.PhoneSegmentInfo;

/**
 * Created by wangjunling on 2017/3/14.
 */
public interface PhoneSegmentInfoRepository extends JpaBaseRepository<PhoneSegmentInfo,String>
{
	PhoneSegmentInfo findByPhoneSegment(String phoneSegment);
}
