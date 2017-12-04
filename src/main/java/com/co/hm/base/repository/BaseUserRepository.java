package com.co.hm.base.repository;

import com.co.hm.base.domain.BaseUser;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * This class serves as inheritance base for user repository.
 * <p>
 * - findByUid: Retrieves an user entity by its uid
 * </p>
 */
@NoRepositoryBean
public interface BaseUserRepository<T extends BaseUser> extends BaseRepository<T> {

  /**
   * Retrieves an user entity by its uid
   *
   * @param uid A column that holds the UID obtained from the next engine API
   * @return the user object if it exists
   */
  T findByUid(String uid);

}
