package com.co.hm.app.configuration;

import com.co.hm.base.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Configuration
 * <p>
 * - authenticationFilter: declare Authentication Filter
 * </p>
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.co.hm.app.repository"})
public class ApplicationConfiguration {

  @Bean
  public FilterRegistrationBean authenticationFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new AuthenticationFilter("/auth/login"));
    registration.addUrlPatterns("/master_goods/*");
    registration.addUrlPatterns("/receive_order_search/*");
    return registration;
  }

}
