package com.co.hm.batches.modules.UpdateToken;

import com.co.hm.app.domain.Company;

import java.util.List;

import com.co.hm.app.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Write token updated to database with data input from UpdateTokenProcessor
 */
public class UpdateTokenWriter implements ItemWriter<Company> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private CompanyRepository companyRepository;

  /**
   * Get data from processor and update to database
   *
   * @param items list companies need to update token into database
   * @throws Exception
   */
  @Override
  public void write(List<? extends Company> items) throws Exception {
    for (Company company : items) {
      logger.info("Writer ========== " + company.toString());
      companyRepository.createOrUpdate(company);
    }
  }

}
