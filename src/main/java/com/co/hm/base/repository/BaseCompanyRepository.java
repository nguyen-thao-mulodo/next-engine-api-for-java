package com.co.hm.base.repository;

import com.co.hm.base.domain.BaseCompany;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * This class serves as inheritance base for company repository.
 * <p>
 * - findByMainFunctionId: Retrieves a company entity by its main Function Id
 * </p>
 */
@NoRepositoryBean
public interface BaseCompanyRepository<T extends BaseCompany> extends BaseRepository<T> {

  /**
   * Retrieves an company entity by its main Function Id
   *
   * @param mainFunctionId Column holds main function company ID
   * @return the Company Object if it exists
   */
  T findByMainFunctionId(String mainFunctionId);

}
