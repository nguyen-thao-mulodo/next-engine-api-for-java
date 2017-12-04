# BaseObject   
##### _com.co.hm.base.domain.BaseObject_  
BaseObjectには全てのモデルに対して必須な属性が３つあります。

 * __id__           : レコードのID、値はデータベースが作成します : `AUTO_INCREMENT`  
 * __created_at__   : レコードの作成時間、値はデータベースが作成します:  `DEFAULT CURRENT_TIMESTAMP`  
 * __updated_at__   : レコードの最新変更時間、、値はデータベースが作成し、更新します: `DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP`  

アプリケーションのモデルは`BaseObject`から受け継ぎますので上記の属性を再度定義する必要ありません。

`@MappedSuperclass`: `BaseObject` の属性はこのクラスから受け継ぐ子クラスのmappingに適用されます。DBにはこの対象用のテーブルがありません。


```java
@Data
@MappedSuperclass
public abstract class BaseObject implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", columnDefinition = "INT(11)")
  private long id;

  @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
  private Date createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
  private Date updatedAt;

}
```