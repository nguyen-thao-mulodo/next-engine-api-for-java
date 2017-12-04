# JobRunner  
##### com.co.hm.batches.modules.JobRunner  

5秒ごとに __Update Token__ ジョブを動かすように設定します。   

_`resources/META-INF/batches/update-token.xml`_   

```xml
  <bean id="updateToken" class="com.co.hm.batches.modules.JobRunner" >
    <property name="job" ref="updateTokenJob"></property>
  </bean>
  <task:scheduled-tasks>
    <task:scheduled ref="updateToken" method="run" cron="*/5 * * * * *" />
  </task:scheduled-tasks>
```

ジョブ実行時点を分別するために `date` 変数を渡します。  

_`com.co.hm.batches.modules.JobRunner`_  

```java
public void runner(Job job) {
    try {
      JobExecution execution = jobLauncher.run(job,
              new JobParametersBuilder().addString("date", new Date().toString()).toJobParameters());
      logger.info("Exit Status : " + execution.getStatus());
    } catch (Exception e) {
      logger.error("error", e);
    }
  }

  /**
   * Run update token batch with schedule from xml config
   */
  public void run() {
    runner(job);
  }
```
