package com.co.hm.app.repository;

import com.co.hm.app.domain.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * The implementation of BaseCompanyRepository
 */
@Repository
public class CompanyRepositoryImpl extends com.co.hm.base.repository.BaseCompanyRepositoryImpl<Company> {

  public CompanyRepositoryImpl(EntityManager em) {
    super(Company.class, em);
  }

}
