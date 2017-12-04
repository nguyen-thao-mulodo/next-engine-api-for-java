package com.co.hm.base.repository;

import com.co.hm.base.domain.BaseUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;

/**
 * Implementation of BaseUserRepository
 * <p>
 * - findByUid: Retrieves an user entity by its uid  <br>
 * - findBy: Retrieves an user entity base on other user's information (uid) <br>
 * </p>
 */
public abstract class BaseUserRepositoryImpl<T extends BaseUser> extends BaseRepositoryImpl<T>
        implements BaseUserRepository<T> {

  public BaseUserRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
  }

  /**
   * Retrieves an user entity by its uid
   *
   * @param uid A column that holds the UID obtained from the next engine API
   * @return the user object if it exists
   */
  @Override
  public T findByUid(String uid) {
    Specification<T> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("uid"), uid);
    return this.findOne(specification);
  }

  /**
   * Retrieves an user entity base on other user's information (uid)
   *
   * @param entity other the object that to check it exists or not
   * @return the user object if it exists
   */
  @Override
  public T findBy(T entity) {
    return this.findByUid(entity.getUid());
  }


}
