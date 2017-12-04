package com.co.hm.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This model contains company information.
 * It is abstract because there is no meaning to instantiate this class.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseCompany extends BaseObject implements Serializable {

  @Column(name = "main_function_id", columnDefinition = "CHAR(128)", nullable = false)
  private String mainFunctionId;

  @Column(name = "platform_id", columnDefinition = "CHAR(128)", nullable = false)
  private String platformId;

  @Column(name = "last_access_token", columnDefinition = "CHAR(128)", nullable = false)
  private String lastAccessToken;

  @Column(name = "last_refresh_token", columnDefinition = "CHAR(128)", nullable = false)
  private String lastRefreshToken;

  @Column(name = "access_token_end_date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
  private String accessTokenEndDate;

  @Column(name = "refresh_token_end_date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
  private String refreshTokenEndDate;

  @Override
  public String toString() {
    return "{ Main Function Id => " + getMainFunctionId() +
            ", Platform Id => " + getPlatformId() +
            ", Last Access Token => " + getLastAccessToken() +
            ", Access Token End Date => " + getAccessTokenEndDate() +
            ", Last Refresh Token => " + getLastRefreshToken() +
            ", Refresh Token End Date => " + getRefreshTokenEndDate() +
            "}";
  }
}
