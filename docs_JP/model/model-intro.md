# モデル紹介 

## データベース構成

 * Vagrantをインストールして、設定してからプロジェクトを実行した後、データベースには`users` と `companies`のテーブルが作成されます。  
 * __User__ と __Company__はNext-engineアプリケーションすべてに必要な基本な対象です。この対象は__認証__に使用されて、  __Next-Engine APIの使用__にも必要です。  


### `users` テーブル 
_Next-engine APIから取得するユーザの幾つかの情報を格納します。_  

* __id__             
* __created_at__    
* __updated_at__    
* __access_token__  : ユーザのログインを認証する情報です。
* __company_id__    : __`companies`__  テーブルからのユーザの企業IDです。
* __refresh_token__ : access_token と同様 です。
* __uid__           : アプリケーションを使用する際のユーザIDです。この情報は`UNIQUE` です。

### Table `companies`  
_Next-engine APIから取得する企業の幾つかの情報を格納します。 このテーブルは主に_ __"ログイン認証の確認・更新のバッチ"__モジュールに使用されます。  


* __id__    
* __created_at__    
* __updated_at__     
* __access_token_end_date__     : access_tokenの有効期限切れ日付です。 (バッチの使用には必要な情報です)    
* __last_access_token__         : 最新のaccess_tokenです。 (使用しているaccess_tokenです。)  
* __last_refresh_token__        : 最新のrefresh_token です。 (使用しているrefresh_token です。)  
* __main_function_id__          : APIから返却される`company_id` です。この情報は`UNIQUE` です。 
* __platform_id__               : APIから返却される`company_ne_id` です。 
* __refresh_token_end_date__    : refresh_tokenの有効期限切れ日付です。  (バッチの使用には必要な情報です)  

## Packages
#### java.com.co.hm.base.domain  

 * __BaseObject__   
 * __BaseCompany__  
 * __BaseUser__  
 
#### java.com.co.hm.app.domain 
 
 * __Company__  
 * __User__  