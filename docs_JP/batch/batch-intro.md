# トークン更新バッチを紹介
__トークン更新バッチ__ は __自動確認とログイン認証情報更新__ 機能を実施します。  

このバッチは5秒ごとに継続的に動かすように設定されます。以下のステップを順に実施します。  

* アプリを利用している企業の情報を取得して、無効の `access_token` と有効の `refresh_token` がある１つの企業を返却します。    
* __NeApiClientService__ を利用し、返却された企業の情報を取得し、
 `access_token`、`refresh_token`、`access_token_end_date`、`refresh_token_end_date`の古い情報をAPIからの返却された新しい情報に設定します。    
* データベースに新規企業の情報を更新します。  
 

## Packages  
#### java.com.co.hm.batches.modules  
   
* JobRunner  

#### java.com.co.hm.batches.modules.UpdateToken  
   
* UpdateTokenReader    
* UpdateTokenProcessor     
* UpdateTokenWriter   
 
 
 