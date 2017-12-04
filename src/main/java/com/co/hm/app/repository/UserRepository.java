package com.co.hm.app.repository;

import com.co.hm.app.domain.User;
import com.co.hm.base.repository.BaseUserRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * The User Repository
 */
public interface UserRepository extends BaseUserRepository<User>, CrudRepository<User, Long> {

}
