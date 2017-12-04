# UpdateTokenWriter  
##### com.co.hm.batches.modules.UpdateToken.UpdateTokenWrite  

__UpdateTokenWrite__ は `updateTokenJob` ジョブの `updateTokenStep1` ステップ内の `Writer` という役目を務めます。以下の操作を実施します。  
```
データベースに (Processorから返却された) 新規企業の情報を更新します。   (trả về từ Processor) vào DB
```  

```java
public class UpdateTokenWriter implements ItemWriter<Company> 
``` 

##### write  
_Hàm write() ファンクションは __`ItemWrite`__インタフェースを実現化する際に必須であって、
データベースに __UpdateTokenProcessor__ から返却された対象企業を保存します_  


```java
@Override
  public void write(List<? extends Company> items) throws Exception {
    for (Company company : items) {
      logger.info("Writer ========== " + company.toString());
      companyRepository.createOrUpdate(company);
    }
  }
```
