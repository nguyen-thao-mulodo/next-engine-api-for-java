package com.co.hm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

import java.util.TimeZone;

@SpringBootApplication(exclude = BatchAutoConfiguration.class)
@ImportResource("classpath:META-INF/spring-configuration.xml")
public class SampleNeSpringbootApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
    SpringApplication.run(SampleNeSpringbootApplication.class, args);
  }
}
