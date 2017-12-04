package com.co.hm.base.repository;

import com.co.hm.base.domain.BaseObject;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Implementation of BaseRepository
 * <p>
 * - createOrUpdate: insert if entity is not existed and update if it exists in the database <br>
 * </p>
 */
public abstract class BaseRepositoryImpl<T extends BaseObject> extends SimpleJpaRepository<T, Long>
        implements BaseRepository<T> {

  public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
  }

  /**
   * insert if entity is not existed and update if it exists in the database
   *
   * @param newEntity the entity to create of update into database
   * @return the entity with full information from database
   */
  @Override
  public T createOrUpdate(T newEntity) {
    T existEntity = findBy(newEntity);
    if (existEntity != null) {
      newEntity.setId(existEntity.getId());
      newEntity.setUpdatedAt(new Date());
      newEntity.setCreatedAt(existEntity.getCreatedAt());
    } else {
      newEntity.setCreatedAt(new Date());
    }
    return this.save(newEntity);
  }

}
