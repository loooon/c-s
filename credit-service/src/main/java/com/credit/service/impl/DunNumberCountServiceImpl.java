package com.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.credit.entity.DunNumberCount;
import com.credit.repository.DunNumberCountRepository;
import com.credit.service.DunNumberCountService;

/**
 * Created by wangjunling on 2017/3/21.
 */
@Service("dunNumberCountService")
public class DunNumberCountServiceImpl implements DunNumberCountService
{
    @Resource
    private DunNumberCountRepository dunNumberCountRepository;

    @Override
    public DunNumberCount searchOne()
    {
        return dunNumberCountRepository.findOne(1);
    }

    @Override
    public void update(DunNumberCount numberCount)
    {
        dunNumberCountRepository.save(numberCount);
    }

    @Override
    public void addCountCoverage(int add)
    {
        dunNumberCountRepository.updateCount(add, DunNumberCount.COVERAGE_COUNT);
    }

    @Override
    public List<DunNumberCount> searchAll()
    {
        return dunNumberCountRepository.findAll();
    }

    @Override
    public void addCountFind(int count)
    {
        dunNumberCountRepository.updateCount(count, DunNumberCount.FIND_COUNT);
    }
}
