package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.DunNumberQueryRecord;

import java.util.List;

/**
 * Created by wangjunling on 2017/3/21.
 */
public interface DunNumberQueryRecordRepository extends JpaBaseRepository<DunNumberQueryRecord, Integer>
{
	List<DunNumberQueryRecord> findByFileMd5(String fileMd5);
}
