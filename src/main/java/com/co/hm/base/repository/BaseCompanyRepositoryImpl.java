package com.co.hm.base.repository;

import com.co.hm.base.domain.BaseCompany;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;

/**
 * Implementation of BaseCompanyRepository
 * <p>
 * - findByMainFunctionId: Retrieves a company entity by its main Function Id <br>
 * - findBy: Retrieves a company entity base on other company's information (mainFunctionId) <br>
 * </p>
 */
public abstract class BaseCompanyRepositoryImpl<T extends BaseCompany> extends BaseRepositoryImpl<T>
        implements BaseCompanyRepository<T> {

  public BaseCompanyRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
  }

  /**
   * Retrieves a company entity by its main Function Id
   *
   * @param mainFunctionId Column holds main function company ID
   * @return the company object if it exists
   */
  @Override
  public T findByMainFunctionId(String mainFunctionId) {
    Specification<T> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("mainFunctionId"), mainFunctionId);
    return this.findOne(specification);
  }

  /**
   * Retrieves a company entity base on other company's information (mainFunctionId)
   *
   * @param other the company object
   * @return the company object if it exists
   */
  @Override
  public T findBy(T other) {
    return findByMainFunctionId(other.getMainFunctionId());
  }
}
