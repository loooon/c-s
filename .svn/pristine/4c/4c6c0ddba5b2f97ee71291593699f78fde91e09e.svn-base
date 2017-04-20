package com.credit.service.impl;

import com.credit.entity.QueryCallDetails;
import com.credit.repository.QueryCallDetailsRepository;
import com.credit.service.QueryCallDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Michael Chan on 4/13/2017.
 */
@Service("queryCallDetailsService")
public class QueryCallDetailsServiceImpl implements QueryCallDetailsService<QueryCallDetails>
{
    @Resource
    private QueryCallDetailsRepository queryCallDetailsRepository;

    @Override
    public List<QueryCallDetails> save(List<QueryCallDetails> queryCallDetails)
    {
        return queryCallDetailsRepository.save(queryCallDetails);
    }
}
