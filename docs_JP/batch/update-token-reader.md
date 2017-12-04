# UpdateTokenReader  
##### com.co.hm.batches.modules.UpdateToken.UpdateTokenReader  

__UpdateTokenReader__ は`updateTokenJob` ジョブの `updateTokenStep1` ステップ内の `Reader` という役目を務めます。以下の操作を実施します。 
```
アプリを利用している企業の情報を取得して、無効の `access_token` と有効の `refresh_token` がある１つの企業を返却します。
```  

```java
public class UpdateTokenReader implements ItemReader<Company> 
``` 


##### setParamsToGetListCompany  
_関数 HashMap を作成して呼び出しの時にAPIに渡します。_  

```java
private List<BasicNameValuePair> setParamsToGetListCompany() {
    return new ArrayList<>(Arrays.asList(
            new BasicNameValuePair("client_id", authClientProperty.getClientId()),
            new BasicNameValuePair("client_secret", authClientProperty.getClientSecret())));
  }
```

##### getListActiveCompany  
_アプリを利用している __企業__ の一覧を取得するために __HttpClientService__ を利用します。_  

```java
private List<String> getListActiveCompany() throws IOException {
    List<HashMap<String, Object>> dataResponse = httpClientService.geDataResponseFromApi(NeApiURL.COMPANY_PATH, setParamsToGetListCompany());

    List<String> companies = new ArrayList<>();
    for (HashMap<String, Object> dictionary : dataResponse) {
      companies.add((String) dictionary.get("company_id"));
    }

    logger.info("Active BaseCompany Id List ========: " + companies);
    return companies;
  }
```

##### isEmptyData  

_データベースより、 __アプリを利用している企業__ の中で無効の `access_token` と有効の `refresh_token` がある __企業__ の一覧を取得します。_
  
```java
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
```

##### read  
_read() ファンクションは __`ItemReader`__ インタフェースを実現化する際に必須であって、 read() ファンクションの対象企業を返却し、
その対象企業が __UpdateTokenProcessor__ クラスで処理されます。_  

```java
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
```