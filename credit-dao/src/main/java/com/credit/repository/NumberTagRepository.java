package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.NumberTag;

import java.util.List;

/**
 * Created by Michael Chan on 3/9/2017.
 */
public interface NumberTagRepository extends JpaBaseRepository<NumberTag,Integer>
{
    List<NumberTag> findAll();
}
