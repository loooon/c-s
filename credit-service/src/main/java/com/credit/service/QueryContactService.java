package com.credit.service;

import com.credit.entity.QueryContact;

import java.util.List;

/**
 * Created by Michael Chan on 4/13/2017.
 */
public interface QueryContactService<T extends QueryContact>
{
    List<QueryContact> save(List<QueryContact> queryContacts);
}
