package com.credit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.credit.entity.CompanyApply;
import com.credit.repository.CompanyApplyRepository;
import com.credit.service.CompanyApplyService;

/**
 * Created by wangjunling on 2017/3/22.
 */
@Service("companyApplyService")
public class CompanyApplyServiceImpl implements CompanyApplyService
{
    @Resource
    private CompanyApplyRepository companyApplyRepository;

    @Override
    public void save(CompanyApply apply)
    {
        companyApplyRepository.save(apply);
    }
}
