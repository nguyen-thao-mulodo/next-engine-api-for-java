package com.co.hm.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This model contains user information.
 * It is abstract because there is no meaning to instantiate this class.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseUser extends BaseObject implements Serializable {

  @Column(name = "company_id", columnDefinition = "INT(11)", nullable = false)
  private long companyId;

  @Column(name = "uid", columnDefinition = "CHAR(128)", nullable = false)
  private String uid;

  @Column(name = "access_token", columnDefinition = "CHAR(128)", nullable = false)
  private String accessToken;

  @Column(name = "refresh_token", columnDefinition = "CHAR(128)", nullable = false)
  private String refreshToken;

  @Override
  public String toString() {
    return "{ Id => " + getId() +
            ", Uid => " + getUid() +
            ", Access Token => " + getAccessToken() +
            ", Refresh Token => " + getRefreshToken() +
            ", BaseCompany Id => " + getCompanyId() +
            "}";
  }
}
