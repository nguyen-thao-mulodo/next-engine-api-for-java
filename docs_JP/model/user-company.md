# User と Company  
##### _com.co.hm.app.domain.User_ | _com.co.hm.app.domain.Company_  


 * `BaseUser` と `BaseCompany` の紹介に記載があります:  
```
対象のユーザと企業が他の属性やメソッドを使用する必要がある際、__`java.com.co.hm.app.domain.User`__と__`java.com.co.hm.app.domain.Company`__クラスのように、`extends`してください。 
```

`User` と`Company`のクラスは`BaseUser` と `BaseCompany`のクラスから属性とメソッドを受け継ぎます。アプリケーションは他の属性やメソッドを使用する必要がある場合、このクラスに宣言する事が出来ます。 

 * DBとのマッピングはこのクラスにて宣言する。



_User.java_  

```java
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", indexes = {
        @Index(name = "uid", columnList = "uid")
})
public class User extends BaseUser implements Serializable {

}
``` 

_Company.java_  

```java
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "companies", indexes = {@Index(name = "main_function_id", columnList = "main_function_id")})
public class Company extends BaseCompany implements Serializable {

}
```