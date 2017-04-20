package com.credit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.credit.entity.DunNumberQueryRecord;
import com.credit.repository.DunNumberQueryRecordRepository;
import com.credit.service.DunNumberQueryRecordService;

import java.util.List;

/**
 * Created by wangjunling on 2017/3/21.
 */
@Service("dunNumberQueryRecordService")
public class DunNumberQueryRecordServiceImpl implements DunNumberQueryRecordService
{
    @Resource
    private DunNumberQueryRecordRepository dunNumberQueryRecordRepository;

    @Override
    public DunNumberQueryRecord save(DunNumberQueryRecord record)
    {
        return dunNumberQueryRecordRepository.save(record);
    }

    @Override
    public List<DunNumberQueryRecord> searchByFileMd5(String fileMd5)
    {
        return dunNumberQueryRecordRepository.findByFileMd5(fileMd5);
    }
}