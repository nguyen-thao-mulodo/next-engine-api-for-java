# バッチ構成  

__Update Token Batch__ の構成は以下の2つのファイルがあります。    

* `resources/META-INF/batches/batch-config.xml`: バッチの共通構成    
* `resources/META-INF/batches/update-token.xml`: Job __update token__ の構成 

#### batch-config.xml  

_このファイルはバッチの動作に必要なコンポーネントを記述しています。その中で以下があります。_    

* _jobRepository_    
* _transactionManager_    
* _jobLauncher_   

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd http://www.springframework.org/schema/batch http://www.springframework.org/schema/batch/spring-batch.xsd http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd">

    <!-- JobRepository and JobLauncher are configuration/setup classes -->
    <bean id="jobRepository" class="org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean">
        <property name="transactionManager" ref="transactionManager"/>
    </bean>

    <bean id="transactionManager"
          class="org.springframework.batch.support.transaction.ResourcelessTransactionManager"/>

    <bean id="jobLauncher" class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
        <property name="jobRepository" ref="jobRepository"/>
    </bean>

    <!--Run batches-->
    <import resource="classpath:META-INF/batches/update-token.xml"/>

</beans>
```

#### update-token.xml  


* _(reader、processor、write) コンポーネントと Job __Update Token__ を記述しています。このジョブは (`updateTokenStep1`) ステップが一つしかありません。_      
* _5秒ごとに job __Update Token__ を動かすように、 __JobRunner__ クラスを設定します。_  


```xml
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:batch="http://www.springframework.org/schema/batch"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:task="http://www.springframework.org/schema/task"
  xsi:schemaLocation="http://www.springframework.org/schema/batch http://www.springframework.org/schema/batch/spring-batch-3.0.xsd
    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
    http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-4.3.xsd">

  <batch:job id="updateTokenJob" >
    <batch:step id="updateTokenStep1">
      <batch:tasklet>
        <batch:chunk
          reader="updateTokenReader"
          processor="updateTokenProcessor"
          writer="updateTokenWriter"
          commit-interval="1">
        </batch:chunk>
      </batch:tasklet>
    </batch:step>
  </batch:job>

  <bean id="updateTokenReader" class="com.co.hm.batches.modules.UpdateToken.UpdateTokenReader"/>
  <bean id="updateTokenProcessor" class="com.co.hm.batches.modules.UpdateToken.UpdateTokenProcessor" />
  <bean id="updateTokenWriter" class="com.co.hm.batches.modules.UpdateToken.UpdateTokenWriter" />

  <!-- Run every 5 seconds -->
  <task:scheduled-tasks>
    <task:scheduled ref="updateToken" method="run" cron="*/5 * * * * *" />
  </task:scheduled-tasks>

  <bean id="updateToken" class="com.co.hm.batches.modules.JobRunner" >
    <property name="job" ref="updateTokenJob"></property>
  </bean>

</beans>
```
