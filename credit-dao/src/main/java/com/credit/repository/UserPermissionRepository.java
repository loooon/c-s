package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.UserPermission;

import java.util.List;

/**
 * Created by Michael Chan on 3/31/2017.
 */
public interface UserPermissionRepository extends JpaBaseRepository<UserPermission,Integer>
{
    List<UserPermission> findByVKey(String vKey);
}
