package com.co.hm.app.repository;

import com.co.hm.app.domain.Company;
import com.co.hm.base.repository.BaseCompanyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The Company Repository
 */
public interface CompanyRepository extends BaseCompanyRepository<Company>, CrudRepository<Company, Long> {

  @Query("SELECT c  FROM Company c WHERE c.accessTokenEndDate < current_time and c.refreshTokenEndDate >= current_time and c.mainFunctionId in (:listCompanyActive)")
  List<Company> findByExpiredToken(@Param("listCompanyActive") List<String> listCompanyActive);
}
