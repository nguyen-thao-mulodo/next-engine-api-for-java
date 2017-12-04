package com.co.hm.services;

import com.co.hm.utils.NeApiURL;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

/**
 * Service interactive api for batches
 * <p>
 * - GetApiResponseFromApi: Get response with type HashMap from api <br>
 * - GeDataResponseFromApi: Get response with type HashMap from field data of api response <br>
 * </p>
 */
@Service
public class HttpClientService {

  /**
   * Get response with type HashMap from api
   *
   * @param apiPath the api path of the Next Engine
   * @param apiParams params to call the api
   * @return the result of the api
   * @throws IOException if has errors when calling the Next Engine API
   */
  public HashMap<String, Object> getApiResponseFromApi(String apiPath, List<BasicNameValuePair> apiParams) throws IOException {

    try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpPost method = new HttpPost(NeApiURL.SERVER_HOST_API + apiPath);
      method.setEntity(new UrlEncodedFormEntity(apiParams, Consts.UTF_8));

      CloseableHttpResponse response = httpClient.execute(method);


      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        return new ObjectMapper().readValue(response.getEntity().getContent(), HashMap.class);
      } else {
        throw new HttpResponseException(
            response.getStatusLine().getStatusCode(),
            response.getStatusLine().getReasonPhrase());
      }
    }
  }

  /**
   * Get response with type HashMap from field data of api response
   *
   * @param apiPath the api path of the Next Engine
   * @param apiParams params to call the api
   * @return the result of the api
   * @throws IOException if has errors when calling the Next Engine API
   */
  public List<HashMap<String, Object>> geDataResponseFromApi(String apiPath, List<BasicNameValuePair> apiParams)
      throws IOException {
    HashMap apiResponse = getApiResponseFromApi(apiPath, apiParams);
    return (List<HashMap<String, Object>>) apiResponse.get("data");
  }

}
