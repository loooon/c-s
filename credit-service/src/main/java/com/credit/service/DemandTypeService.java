package com.credit.service;

import java.util.List;

import com.credit.entity.DemandType;

/**
 * Created by Michael Chan on 3/10/2017.
 */
public interface DemandTypeService<T extends DemandType>
{
    List<DemandType> findAll();
}
