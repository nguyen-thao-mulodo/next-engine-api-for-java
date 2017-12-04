package com.co.hm.base.repository;

import com.co.hm.base.domain.BaseObject;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * This class serves as inheritance base for all repositories.
 * <p>
 * - findBy: Retrieves an entity base on other entity's information <br>
 * - createOrUpdate: insert if entity is not existed and update if it exists in the database <br>
 * </p>
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseObject> {

  /**
   * Retrieves an entity base on other entity's information
   *
   * @param other the object that to check it exists or not
   * @return entity if it existed in the database
   */
  T findBy(T other);

  /**
   * insert if entity is not existed and update if it exists in the database
   *
   * @param newEntity the entity to create of update into database
   * @return the entity with full information from database
   */
  T createOrUpdate(T newEntity);

}
