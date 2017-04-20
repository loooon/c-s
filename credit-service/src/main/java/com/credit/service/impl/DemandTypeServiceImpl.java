package com.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.credit.entity.DemandType;
import com.credit.repository.DemandTypeRepository;
import com.credit.service.DemandTypeService;
import org.springframework.stereotype.Service;

/**
 * Created by Michael Chan on 3/10/2017.
 */
@Service("demandTypeService")
public class DemandTypeServiceImpl implements DemandTypeService<DemandType>
{
    @Resource
    private DemandTypeRepository demandTypeRepository;

    @Override
    public List<DemandType> findAll()
    {
        return demandTypeRepository.findAll();
    }
}
