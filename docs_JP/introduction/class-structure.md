# クラス構成 

__`com.co.hm.base`__パッケージにはプロジェクト全体のモデル、コントローラー、レポジトリーが含まれます。

__`com.co.hm.app`__パッケージはアプリケーションの処理を実施するモデル、コントローラー、レポジトリーを格納します。
このクラスは__`com.co.hm.base`__  パッケージのクラスから受け継ぎます。

### _モデル_  
##### Base: com.co.hm.base.domain  

_`BaseCompany` と `BaseUser` のクラスは`BaseObject`クラスから受け継ぐ事もあります_        

 * BaseObject    
 * BaseCompany    
 * BaseUser    
 
##### Application: com.co.hm.app.domain:
  
 * Company  
 * User  


### _レポジトリー_  
#####Base: com.co.hm.base.repository  
_ `BaseCompanyRepository` と`BaseUserRepository` のインターフェイスは
`BaseRepository`のインターフェイスから受け継ぐ事もあります_  

 * BaseRepository  
 * BaseCompanyRepository  
 * BaseUserRepository  
 
##### Application: com.co.hm.app.repository    
 * CompanyRepository   
 * UserRepository   

###_コントローラー_   
##### Base: com.co.hm.base.web  
  
 * BaseController  
 
##### Application: com.co.hm.app  
    
_アプリケーションの`BaseController`は`com.co.hm.base.web`パッケージの`BaseController`を受け継いで、
アプリケーションに使用する２つモデルのクラスである２つパラメーターで　constructorを使用します
(`com.co.hm.app.domain.User` と `com.co.hm.app.domain.Company`)_.  
 
  
  
_コントローラーはアプリケーションの`BaseController` をまた受け継ぎます_.  

 * BaseController   
 * AuthController  
 * MasterGoodsController    
 * ReceiveOrderController     
 * _IndexController(現在インデックスページの内容を表示操作にしか使用されません。`BaseController`を受け継ぎません)_  