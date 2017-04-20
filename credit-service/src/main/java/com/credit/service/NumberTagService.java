package com.credit.service;

import com.credit.entity.DemandType;
import com.credit.entity.NumberTag;
import com.credit.entity.OrganizationType;

import java.util.List;

/**
 * Created by Michael Chan on 3/9/2017.
 */
public interface NumberTagService<T extends NumberTag>
{
    List<NumberTag> searchAll();
}
