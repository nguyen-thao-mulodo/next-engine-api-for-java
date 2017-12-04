# コントローラーを紹介  

アプリの機能ごとには処理コントローラーが一つあります。  

* __`AuthController`__：確認してネクストエンジンへのログインを催促します。    
* __`ReceiveOrderController`__:__`receiveorder_base`__ を検索します。   
* __`MasterGoodsController`__: (.csv) ファイルの一つをアップロードします。   

__`java.com.co.hm.base.web.BaseController`__ には全機能の必須な処理の操作が含まれます。
コントローラーはこのクラスから引き継ぐアプリの詳細機能・操作を処理します。    

## Packages  
#### java.com.co.hm.base.web    

 * __BaseController__   
 
 
#### java.com.co.hm.app.web  
 
 * __BaseController__   
 * __IndexController__   
 * __AuthController__  
 * __ReceiveOrderController__  
 * __MasterGoodsController__   
  
    
## NeApiClientService  
#### com.co.hm.services.NeApiClietnService  
中間クラスはネクストエンジンのSDKを通してAPIを呼び出すために利用されます。     
(https://developer.next-engine.com/sdk#require) [こちらで] 参照 。