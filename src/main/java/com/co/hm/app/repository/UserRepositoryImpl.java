package com.co.hm.app.repository;

import com.co.hm.app.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * The implementation of BaseUserRepository
 */
@Repository
public class UserRepositoryImpl extends com.co.hm.base.repository.BaseUserRepositoryImpl<User> {

  public UserRepositoryImpl(EntityManager em) {
    super(User.class, em);
  }


}
