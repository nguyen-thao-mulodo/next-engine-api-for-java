# MasterGoodsController  
##### java.com.co.hm.base.web.MasterGoodsController  
`MasterGoodsController` は__(.csv)ファイルをアップロードする__ 機能を実施する操作を処理する。 

##### masterGoods  

_ __`https://localhost:8443/master_goods`__ が呼び出される際、__`resources/templates/MasterGoods/index.html`__ のテンプレートを使用して、ページの内容を表示させます_    

```java
@RequestMapping(value = "", method = RequestMethod.GET)
  public String masterGoods() {
    return "MasterGoods/index";
  }
```

##### showMasterGoods  

_ __` https://localhost:8443/master_goods/show `__ が呼び出される際、__` resources/templates/MasterGoods/show.html `__ のテンプレートを使用して、ページの内容を表示させます_   

```java
@RequestMapping(value = "/show", method = RequestMethod.GET)
  public String showMasterGoods() {
    return "MasterGoods/show";
  }
```

##### uploadMasterGoods  

_ __`https://localhost:8443/master_goods/upload`__ が呼び出される際、ファイルを確認し、アップロードしてから__`https://localhost:8443/master_goods/show`___ページへリダイレクトさせます_

```java
@RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String uploadMasterGoods(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
    logger.info("File info ======== " + file.getSize());
    if (!FileUtils.uploadFile(file)) {
      redirectAttributes.addFlashAttribute("message", getMessage("choose_file_to_upload"));
    } else {
      execApiUploadMasterGoods(request, response, redirectAttributes, file.getOriginalFilename());
    }
    return "redirect:/master_goods/show";
  }
```

##### showMasterGoods  

_ __`https://localhost:8443/master_goods/download`__ が呼び出される際、 `master_goods.csv`のサンプルファイルを返却します_   

```java
@RequestMapping(value = "/download", method = RequestMethod.GET)
  public @ResponseBody
  FileSystemResource downloadCSVFile(HttpServletRequest request,
                                     HttpServletResponse response) {

    return FileUtils.downloadCSVFile(response, Constant.UPLOADED_FORDER, CSV_MASTER_GOODS);
  }
```

##### execApiUploadMasterGoods  

_ __NeApiClienService__を使用してファイルをアップロードする_
  
```java
private void execApiUploadMasterGoods(HttpServletRequest request,
                                        HttpServletResponse response,
                                        RedirectAttributes redirectAttributes,
                                        String fileName) {
    try {
      HashMap<String, Object> apiResponse = neApiExecuteAndSaveToken(request, getCurrentToken(request),
              NeApiURL.MASTER_GOODS_UPLOAD_PATH, createUploadMasterGoodsParams(fileName));
      if (apiResponse != null) {
        redirectAttributes.addFlashAttribute("data", ConvertUtils.convertOb2String(apiResponse));
      }
    } catch (Exception e) {
      logger.error("error", e);
    }
  }
```


##### createUploadMasterGoodsParams  

_呼び出す際、APIに渡すパラメーターのHashMapを作成します_

```java
private HashMap<String, String> createUploadMasterGoodsParams(String fileName) {
    HashMap<String, String> apiParams = new HashMap<>();
    apiParams.put("wait_flag", "1");
    apiParams.put("data_type", "csv");
    apiParams.put("data", StringUtils.readTextFileToString(fileName));
    return apiParams;
  }
```