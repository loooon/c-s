package com.credit.service.impl;

import com.credit.entity.QueryContact;
import com.credit.repository.QueryContactRepository;
import com.credit.service.QueryContactService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Michael Chan on 4/13/2017.
 */
@Service("queryContactService")
public class QueryContactServiceImpl implements QueryContactService<QueryContact>
{
    @Resource
    private QueryContactRepository queryContactRepository;
    @Override
    public List<QueryContact> save(List<QueryContact> queryContacts)
    {
        return queryContactRepository.save(queryContacts);
    }
}
