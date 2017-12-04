# UpdateTokenProcessor  
##### com.co.hm.batches.modules.UpdateToken.UpdateTokenProcessor  

__UpdateTokenProcessor__ は `updateTokenJob` ジョブの `updateTokenStep1` ステップ内の `Processor` という役目を務めます。以下の操作を実施します。  
```
 __NeApiClientService__ を利用し、 (Readerから返却された) 企業の情報を取得し、
 `access_token`、`refresh_token`、`access_token_end_date`、`refresh_token_end_date`の古い情報をAPIからの返却された新しい情報に設定します。
```  

```java
public class UpdateTokenProcessor implements ItemProcessor<Company, Company>  
```

##### fetchCompanyInfo  
___NeApiClientService__ を利用して企業の情報を取得します。_  

```java
private HashMap<String, Object> fetchCompanyInfo(String accessToken, String refreshToken) {
    return neApiClientService.neApiExecute(NeApiURL.COMPANY_INFO_PATH,
            accessToken, refreshToken);
  }
```


##### process  
_process() ファンクションは __`ItemProcessor`__インタフェースを実現化する際に必須です。_    
___NeApiClientService__ を利用し、 __UpdateTokenReader__ から返却された企業の情報を取得し、
  `access_token`、`refresh_token`、`access_token_end_date`、`refresh_token_end_date`の古い情報をAPIからの返却された新しい情報に設定します。 _    
_ process() ファンクションの対象企業は返却され、引き続き __UpdateTokenWriter__ クラスで処理されます。_  

```java
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
```


