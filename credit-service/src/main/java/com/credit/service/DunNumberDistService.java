package com.credit.service;

import com.credit.entity.DunNumberDist;

import java.util.List;

/**
 * Created by Michael Chan on 3/24/2017.
 */
public interface DunNumberDistService<T extends DunNumberDist>
{
    List<DunNumberDist> searchAllByUserPhone(String userPhone);

    List<String> searchAllUserPhones();

    List<DunNumberDist> searchByIds(List<Integer> ids);

    List<DunNumberDist> searchAll();

    List<DunNumberDist> searchRandomDist(List<String> fromProvinces,List<String> toProvinces);
}
