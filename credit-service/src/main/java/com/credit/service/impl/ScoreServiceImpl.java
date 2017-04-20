package com.credit.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.credit.entity.Score;
import com.credit.repository.ScoreRepository;
import com.credit.service.ScoreService;

/**
 * Created by Michael Chan on 3/27/2017.
 */
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService<Score>
{
    @Resource
    private ScoreRepository scoreRepository;

    @Override
    public Double searchByVarNameAndValueIn(String varName, Integer value)
    {
        return scoreRepository.findByVarNameAndValueIn(varName, value);
    }

    @Override
    public Integer search(Map<String, Integer> varNameValue)
    {
        if (varNameValue == null || varNameValue.size() == 0)
        {
            return 0;
        }
        Double totalScore = 0d;
        for (Map.Entry<String, Integer> entry : varNameValue.entrySet())
        {
            Double score = scoreRepository.findByVarNameAndValueIn(entry.getKey(), entry.getValue());
            totalScore += score == null ? 0 : score;
        }
        return totalScore.intValue();
    }
}
