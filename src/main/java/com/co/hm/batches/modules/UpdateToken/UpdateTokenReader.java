package com.co.hm.batches.modules.UpdateToken;

import com.co.hm.app.domain.Company;
import com.co.hm.app.repository.CompanyRepository;
import com.co.hm.property.AuthClientProperty;
import com.co.hm.services.HttpClientService;
import com.co.hm.utils.NeApiURL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Read data from database to prepare update token
 */
public class UpdateTokenReader implements ItemReader<Company> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected AuthClientProperty authClientProperty;

  @Autowired
  private CompanyRepository companyRepository;

  private List<Company> companyData = Collections.emptyList();
  private int nextCompanyIndex;

  @Autowired
  private HttpClientService httpClientService;

  /**
   * Push company need update token to processor
   *
   * @return the company need to update token
   * @throws Exception
   */
  @Override
  public Company read() throws Exception {
    // If nodata return null and end batch
    if ((companyData.isEmpty() && isEmptyData()) || (companyData.size() == nextCompanyIndex)) {
      companyData = Collections.emptyList();
      return null;
    }

    nextCompanyIndex++;

    logger.info("READER ============= " + ((Company) companyData.get(nextCompanyIndex - 1)).toString());
    return (Company) companyData.get(nextCompanyIndex - 1);
  }

  /**
   * Check list company active empty or not
   *
   * @return true if the list company is empty
   */
  private boolean isEmptyData() {
    try {
      List<String> listCompanyActive = getListActiveCompany();
      companyData = companyRepository.findByExpiredToken(listCompanyActive);
      nextCompanyIndex = 0;
      return false;
    } catch (Exception e) {
      logger.error("error", e);
      logger.info("============ No company Active ============");
      companyData = Collections.emptyList();
      return true;
    }
  }

  /**
   * Get list company active from api /api_app/company
   *
   * @return list company active from api /api_app/company
   * @throws IOException
   */
  private List<String> getListActiveCompany() throws IOException {
    List<HashMap<String, Object>> dataResponse = httpClientService.geDataResponseFromApi(NeApiURL.COMPANY_PATH, setParamsToGetListCompany());

    List<String> companies = new ArrayList<>();
    for (HashMap<String, Object> dictionary : dataResponse) {
      companies.add((String) dictionary.get("company_id"));
    }

    logger.info("Active BaseCompany Id List ========: " + companies);
    return companies;
  }

  /**
   * Set param for api /api_app/company
   *
   * @return the param to call Next Engine API
   */
  private List<BasicNameValuePair> setParamsToGetListCompany() {
    return new ArrayList<>(Arrays.asList(
            new BasicNameValuePair("client_id", authClientProperty.getClientId()),
            new BasicNameValuePair("client_secret", authClientProperty.getClientSecret())));
  }

}
