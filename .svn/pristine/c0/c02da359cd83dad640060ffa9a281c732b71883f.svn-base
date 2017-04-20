package com.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.credit.entity.NumberTag;
import com.credit.repository.NumberTagRepository;
import com.credit.service.NumberTagService;

/**
 * Created by Michael Chan on 3/9/2017.
 */
@Service("numberTagService")
public class NumberTagServiceImpl implements NumberTagService<NumberTag>
{
    @Resource
    private NumberTagRepository numberTagRepository;

    @Override
    public List<NumberTag> searchAll()
    {
        return numberTagRepository.findAll();
    }
}
