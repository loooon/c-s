package com.credit.service.impl;

import com.credit.entity.QueryLog;
import com.credit.repository.QueryLogRepository;
import com.credit.service.QueryLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Michael Chan on 4/13/2017.
 */
@Service("queryLogService")
public class QueryLogServiceImpl implements QueryLogService<QueryLog>
{
    @Resource
    private QueryLogRepository queryLogRepository;

    @Override
    public QueryLog save(QueryLog queryLog)
    {
        return queryLogRepository.save(queryLog);
    }
}
