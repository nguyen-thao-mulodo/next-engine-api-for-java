# ReceiveOrderController  
##### java.com.co.hm.base.web.ReceiveOrderController  

`ReceiveOrderController`は__Shop ID = 1__と__Receive Order Date > 2015-01-19 00:00:00__  のデモの検索条件で__`receiveorder_base`__ __検索__機能を実施する操作を処理します。

##### searchReceiveOrder  

__`https://localhost:8443/receive_order_search`__が呼び出される際、ページ内容を表示するには__`resources/templates/show.html`__のテンプレートを使用します。 

```java
 @RequestMapping(value = "/receive_order_search", method = RequestMethod.GET)
  public String searchReceiveOrder(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model) {
    execApiSearchReceiveOrder(request, response, model);
    return "show";
  }
```

##### execApiSearchReceiveOrder  

_ __NeApiClientService__を使用してAPIからのデータを取得して、モデルにデータを渡して`resources/templates/show.html`.のテンプレートに表示させるようにします_  
```java
 private void execApiSearchReceiveOrder(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Model model) {
     try {
       HashMap<String, Object> apiResponse = neApiExecuteAndSaveToken(request,
               getCurrentToken(request),
               NeApiURL.RECEIVE_ORDER_SEARCH_PATH,
               createSearchReceiveOrderParams());
       if (apiResponse != null) {
         model.addAttribute("data", ConvertUtils.convertOb2String(apiResponse));
       }
     } catch (Exception e) {
       logger.error("error", e);
     }
   }
```

##### createSearchReceiveOrderParams  

_呼び出す際、APIに渡すパラメーターのHashMapを作成します_

```java
 private HashMap<String, String> createSearchReceiveOrderParams() {
     HashMap<String, String> apiParams = new HashMap<>();
 
     // Condition : receive_order_id = 1
     apiParams.put("receive_order_shop_id-eq", "1");
 
     // Condition : receive_order_date > 2015-01-19 00:00:00
     apiParams.put("receive_order_date-gt", "2015-01-19 00:00:00");
 
     // Field is selected
     apiParams.put("fields", "receive_order_shop_id,receive_order_id,receive_order_shop_cut_form_id,receive_order_date,receive_order_import_date");
 
     return apiParams;
   }
```