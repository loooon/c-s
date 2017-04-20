package com.credit.service.impl;

import com.credit.entity.AccessLog;
import com.credit.repository.AccessLogRepository;
import com.credit.service.AccessLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Michael Chan on 3/31/2017.
 */
@Service("accessLogService")
public class AccessLogServiceImpl implements AccessLogService<AccessLog>
{
    @Resource
    private AccessLogRepository accessLogRepository;

    @Override
    public void saveAccessRecord(AccessLog accessLog)
    {
        accessLogRepository.save(accessLog);
    }
}
