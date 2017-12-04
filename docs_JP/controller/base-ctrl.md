# BaseController  
##### java.com.co.hm.base.web.BaseController  

__`BaseController`__ のファンクションは以下の作業に基本操作を処理します。  

* `NeApiClientService`　を利用してAPIからの結果を取得します。  
* ネクストエンジンの __ログイン認証__ に利用するために、セッションのトークン変数を確認・保存・処理します。  
* __企業・ユーザ__ の情報を取得します。  


コントローラーはこのクラスから引き継ぐアプリの詳細機能・操作を処理します。  


##### getCurrentToken  

_セッションに保存されている (access_token、refresh_token) トークンの情報を取得します。_  

```java
public NeToken getCurrentToken(HttpServletRequest request) {

    return (NeToken) request.getSession().getAttribute(NeToken.class.getName());
  }
```

##### neLogin

_ネクストエンジンへのログインを催促するために __NeApiClientService__ を利用します。_  

```java
public HashMap<String, Object> neLogin(HttpServletRequest request, HttpServletResponse response, String referrerPath) {
    return neApiClientService.requireLogin(request, response, authClientProperty.getClientId(),
            authClientProperty.getClientSecret(), referrerPath);
  }
```

##### neApiExecute

_APIを呼び出し戻された結果を返却するために __NeApiClientService__ を利用します。_   

```java
public HashMap<String, Object> neApiExecute(NeToken token,
                                              String apiUrlPath,
                                              HashMap<String, String> apiParams)  {
    if (apiParams == null) {
      return neApiClientService.neApiExecute(apiUrlPath, token.getAccessToken(), token.getRefreshToken());
    } else {
      return neApiClientService.neApiExecute(apiUrlPath, apiParams, token.getAccessToken(), token.getRefreshToken());
    }
  }
```

##### neApiExecuteAndSaveToken

_APIを呼び出しトークンを保存し戻された結果を返却するために __NeApiClientService__ を利用します。_   

```java
public HashMap<String, Object> neApiExecuteAndSaveToken(HttpServletRequest request,
                                                          NeToken token,
                                                          String apiUrlPath,
                                                          HashMap<String, String> apiParams)
      throws IllegalAccessException, InstantiationException {

    HashMap<String, Object> apiResponse = neApiExecute(token, apiUrlPath, apiParams);
    saveToken(request, token, apiResponse);

    return apiResponse;
  }
```


##### saveToken

_返却されるAPIからトークンの情報を取得し、セッションに保存し、取得されたトークンの企業とユーザの情報を取得してデータベースに保存します。_  
 
```java
public void saveToken(HttpServletRequest request, NeToken currentToken,
                        HashMap<String, Object> apiResponse) throws InstantiationException, IllegalAccessException{
    Object token = apiResponse.get(NeApiClient.KEY_ACCESS_TOKEN);
    if (token == null) {
      return;
    }
    if (currentToken != null && token.toString().equals(currentToken.getAccessToken())) {
      return;
    }
    saveTokenToSession(request, currentToken, apiResponse);
    C company = createCompany(apiResponse.get(NeApiClient.KEY_ACCESS_TOKEN).toString(), apiResponse.get(NeApiClient.KEY_REFRESH_TOKEN).toString());
    createUser(company.getId(), apiResponse.get(NeApiClient.KEY_ACCESS_TOKEN).toString(), apiResponse.get(NeApiClient.KEY_REFRESH_TOKEN).toString());
  }
```

##### saveTokenToSession

_セッションにトークンの情報を保存します。_   

```java
public void saveTokenToSession(HttpServletRequest request, NeToken token, HashMap<String, Object> apiResponse) {
    NeToken neToken = token;
    if (token == null) {
      neToken = new NeToken();
    }
    neToken.setAccessToken(apiResponse.get(NeApiClient.KEY_ACCESS_TOKEN).toString());
    neToken.setRefreshToken(apiResponse.get(NeApiClient.KEY_REFRESH_TOKEN).toString());
    request.getSession().setAttribute(NeToken.class.getName(), neToken);
  }
```

##### fetchCompanyInfo

_APIから企業の情報を取得するために __NeApiClientService__ を利用します。_   

```java
public HashMap<String, Object> fetchCompanyInfo(String accessToken, String refreshToken) {
    return neApiClientService.neApiExecute(NeApiURL.COMPANY_INFO_PATH,
            accessToken, refreshToken);
  }
```

##### fetchUserInfo

_APIからユーザの情報を取得するために __NeApiClientService__ 利用します。_   

```java
public HashMap<String, Object> fetchUserInfo(String accessToken, String refreshToken) {
    return neApiClientService.neApiExecute(NeApiURL.USER_INFO_PATH,
            accessToken, refreshToken);
  }
```

##### getMessage

___`resources.messages_us.properties`__ からメッセージを取得します。_   

```java
public String getMessage(String messages) {
    return messageSource.getMessage(messages, new String[]{}, Locale.US);
  }
```


#####createCompany

_APIから企業の情報を取得してデータベースに保存するために __NeApiClientService__ を利用します。_   

```java
C createCompany(String accessToken, String refreshToken) throws InstantiationException, IllegalAccessException{
    C company = companyClass.newInstance();
    HashMap<String, Object> apiResponse = fetchCompanyInfo(accessToken, refreshToken);
    List<HashMap<String, Object>> listDataResponse = (ArrayList<HashMap<String, Object>>) apiResponse.get("data");
    HashMap<String, Object> dataResponse = listDataResponse.get(0);

    company.setAccessTokenEndDate(apiResponse.get("access_token_end_date").toString());
    company.setRefreshTokenEndDate(apiResponse.get("refresh_token_end_date").toString());

    company.setMainFunctionId(dataResponse.get("company_id").toString());
    company.setPlatformId(dataResponse.get("company_ne_id").toString());
    company.setLastAccessToken(accessToken);
    company.setLastRefreshToken(refreshToken);

    logger.info("BaseCompany info ==============" + company.toString());
    return companyRepository.createOrUpdate(company);
  }
```

#####createUser

_APIからユーザの情報を取得してデータベースに保存するために __NeApiClientService__ を利用します。_   

```java
U createUser(long companyId, String accessToken, String refreshToken) throws InstantiationException, IllegalAccessException{
    U user = userClass.newInstance();
    List<HashMap<String, Object>> listDataResponse = (ArrayList<HashMap<String, Object>>) fetchUserInfo(accessToken, refreshToken).get("data");
    HashMap<String, Object> dataResponse = listDataResponse.get(0);

    user.setCompanyId(companyId);
    user.setUid(dataResponse.get("uid").toString());

    user.setAccessToken(accessToken);
    user.setRefreshToken(refreshToken);

    logger.info("BaseUser info ==============" + user.toString());
    return userRepository.createOrUpdate(user);
  }
```

#####getReferrerPath

_(ユーザがログインを催促される前に利用したページ) 参照パースを取得します。ネクストエンジンにログインした後に `AuthController` の __callback__ ファンクションはその参照ページへリダイレクトします。_  
 
```java
public String getReferrerPath(HttpServletRequest request) {
    if (request.getParameter("path") != null) {
      return request.getParameter("path");
    }
    return Constant.ROOT_PATH;
  }
```
