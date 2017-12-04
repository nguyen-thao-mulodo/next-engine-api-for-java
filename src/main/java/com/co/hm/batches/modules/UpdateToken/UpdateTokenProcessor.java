package com.co.hm.batches.modules.UpdateToken;

import com.co.hm.app.domain.Company;
import com.co.hm.services.NeApiClientService;
import com.co.hm.utils.NeApiURL;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Process data of batch update token from UpdateTokenReader and then pass data to UpdateTokenWriter
 */
public class UpdateTokenProcessor implements ItemProcessor<Company, Company> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected NeApiClientService neApiClientService;

  /**
   * Get info from api /api_v1_login_company/info
   *
   * @param accessToken the access token of the logged-in user
   * @param refreshToken the refresh token of the logged-in user
   * @return the company information from the Next Engine API
   * @throws Exception if has errors when calling the Next Engine API
   */
  private HashMap<String, Object> fetchCompanyInfo(String accessToken, String refreshToken) {
    return neApiClientService.neApiExecute(NeApiURL.COMPANY_INFO_PATH,
            accessToken, refreshToken);
  }

  /**
   * Update token for company expired token
   *
   * @param company the company need to update token
   * @return the company if process success
   * @throws Exception if has errors when calling the Next Engine API
   */
  @Override
  public Company process(Company company) throws Exception {
    HashMap<String, Object> apiResponse = fetchCompanyInfo(company.getLastAccessToken(), company.getLastRefreshToken());

    if ("error".equals(apiResponse.get("result").toString())) {
      logger.info("RESPONSE =============== " + apiResponse.toString());
      return null;
    }

    company.setLastAccessToken(apiResponse.get("access_token").toString());
    company.setLastRefreshToken(apiResponse.get("refresh_token").toString());
    company.setAccessTokenEndDate(apiResponse.get("access_token_end_date").toString());
    company.setRefreshTokenEndDate(apiResponse.get("refresh_token_end_date").toString());

    logger.info("Processor ========== " + company.toString());
    return company;
  }
}
