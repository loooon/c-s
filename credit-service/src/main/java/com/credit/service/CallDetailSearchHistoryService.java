package com.credit.service;

import com.credit.entity.UserCallDetailsHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Michael Chan on 3/21/2017.
 */
public interface CallDetailSearchHistoryService<T extends UserCallDetailsHistory>
{
    Page<UserCallDetailsHistory> searchUserCallDetailHistory(final Integer userId, Pageable pageable);

    void saveUserCallDetailHistory(UserCallDetailsHistory userCallDetailsHistory);

    UserCallDetailsHistory searchOneUserCallDetail(Integer id);

    List<UserCallDetailsHistory> searchAll();

    List<UserCallDetailsHistory> searchByMd5(String fileMd5Digest);
}
