package com.credit.service;

import com.credit.entity.CallDetails;
import com.credit.entity.Score;

import java.util.List;
import java.util.Map;

/**
 * Created by Michael Chan on 3/27/2017.
 */
public interface ScoreService<T extends Score>
{
    Double searchByVarNameAndValueIn(String varName, Integer value);

	Integer search(Map<String, Integer> varNameValue);
}
