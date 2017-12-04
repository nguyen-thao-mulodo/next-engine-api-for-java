# IndexController  
##### java.com.co.hm.base.web.IndexController  
`IndexController` はアプリのインデックスページの内容表示を操作するために利用されます。   

#### index  
___`https://localhost:8443`__ にアクセスする際、 __`resources/templates/index.html`__ テンプレートを利用してページの内容を表示します。_  

```java
@Controller
public class IndexController {

  @RequestMapping("")
  public String index() {
    return "index";
  }

}
```