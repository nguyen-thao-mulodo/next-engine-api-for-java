# BaseUser と BaseCompany  
##### _com.co.hm.base.domain.BaseUser_ | _com.co.hm.base.domain.BaseCompany_  

 * BaseUser と BaseCompanyにはDBの__`users`__ と __`companies`__のテーブルに該当する属性が含まれます。next-engineアプリケーション全てにも必要な基本な属性です。   
 * `id`, `created_at`, `updated_at` の基本な３つ属性は`BaseObject` から受け継ぎました。  
 * 対象のユーザと企業が他の属性やメソッドを使用する必要がある際、__`java.com.co.hm.app.domain.User`__と__`java.com.co.hm.app.domain.Company`__クラスのように、`extends`してください。 
 * `@MappedSuperclass`: この対象の属性は受け継ぐ子クラスのマッピングに使用されます。DBにはこの対象のテーブルがありません。


_BaseUser.java_
```java
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
```


_BaseCompany.java_
```java
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
```