package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.UserAuthIp;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Michael Chan on 3/14/2017.
 */
public interface UserAuthIpRepository extends JpaBaseRepository<UserAuthIp,Integer>
{
    @Query("select authorizedIp from UserAuthIp where userVkey =?1")
    List<String> findByVKey(String userVkey);
}
