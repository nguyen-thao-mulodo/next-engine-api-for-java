# AuthController  
##### java.com.co.hm.base.web.AuthController  

`AuthController` は __確認・ネクストエンジンへのログインを催促__ 機能を実施する操作を処理します。 


##### callback  

_ユーザがネクストエンジンのログインページにリダイレクトされログインに成功してから、ネクストエンジンは __`https://localhost:8443/callback`__ にリダイレクトします。
ここでは、確認してセッションにトークンを保存し、 (ユーザが利用していたページ) 参照パースにリダイレクトします。_  

```java
@RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(HttpServletRequest request, HttpServletResponse response, RedirectAttributes messages) {
        logger.info("UID   ==============" + request.getParameter("uid"));
        logger.info("STATE ==============" + request.getParameter("state"));
        logger.info("PATH  ==============" + getReferrerPath(request));

        if (request.getParameter("uid") == null || request.getParameter("state") == null) {
            return "redirect:" + Constant.ROOT_PATH;
        }

        // Get accessToken and refreshToken
        HashMap<String, Object> apiResponse = neLogin(request, response, authClientProperty.getRedirectUrl());
        if (apiResponse != null) {
            try {
                saveToken(request, new NeToken(), apiResponse);
                messages.addFlashAttribute("data", getMessage("logged_in_success"));
            } catch (Exception e) {
                messages.addFlashAttribute("data", getMessage("st_error"));
                logger.error("error", e);
            }
        }

        return "redirect:" + getReferrerPath(request);
    }
```

##### login  

_ユーザがセッションにトークンの情報がまだないアプリの機能を利用する際、
フィルータは __`https://localhost:8443/auth/login`__ にリダイレクトして、ネクストエンジンへのログインを催促するために __NeApiClientService__ を利用します。_  

```java
@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getParameter("path");
        String query = "";
        if (path != null && !path.isEmpty()) {
            query = "?path=" + path;
        }
        neLogin(request, response, authClientProperty.getRedirectUrl() + query);
        return null;
    }
```