package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByPhoneNumber(String phoneNumber);

/**
 * c:添加
 * u：更新
 * r：查询：list/map geOneByid/name
 * d：删除:
 *
 *
 * */

}
