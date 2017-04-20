package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.User;


/**
 * Created by wangjunling on 2017/3/7.
 */
public interface UserRepository extends JpaBaseRepository<User,Integer>
{
    User findByUserName(String userName);

	User findByVKey(String vkey);

    User findByPhone(String phone);
}
