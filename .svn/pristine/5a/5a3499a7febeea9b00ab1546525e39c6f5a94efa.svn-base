package com.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.credit.entity.UserAuthIp;
import com.credit.repository.UserAuthIpRepository;
import com.credit.service.UserAuthIpService;

/**
 * Created by Michael Chan on 3/14/2017.
 */
@Service("userAuthIpService")
public class UserAuthIpServiceImpl implements UserAuthIpService<UserAuthIp>
{
    @Resource
    UserAuthIpRepository userAuthIpRepository;

    @Override
    public List<String> searchAuthIpsByVKey(String vKey)
    {
        return userAuthIpRepository.findByVKey(vKey);
    }

    @Override
    public List<UserAuthIp> searchAll()
    {
        return userAuthIpRepository.findAll();
    }
}
