
package com.co.hm.property;

import lombok.Data;

/**
 * Bean mapping ne-api.properties and object
 */
@Data
public class AuthClientProperty {

  private String clientId;
  private String clientSecret;
  private String redirectUrl;
  private String domainPath;

}
