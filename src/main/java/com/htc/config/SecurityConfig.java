package com.htc.config;

import com.htc.application.filters.AuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final AuthenticationFilter authenticationFilter;

  private final String logoutUrl = "/api/auth/logout";

  private final String[] permittedPaths = {
          "/api/auth/login",
          "/api/auth/access-token",
          "/swagger-ui/**",
          "/v3/api-docs/**"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .sessionManagement(config -> config
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeRequests(config -> config
                    .antMatchers(this.permittedPaths).permitAll()
                    .anyRequest().authenticated())
            .logout(config -> config
                    .logoutUrl(this.logoutUrl).permitAll()
                    // Отключить перенаправление после выхода из системы.
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
            .addFilterAfter(
                    this.authenticationFilter,
                    BasicAuthenticationFilter.class);
  }

  @Bean
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(100);
    executor.setQueueCapacity(50);
    executor.setThreadNamePrefix("async-");
    return executor;
  }

  @Bean
  public DelegatingSecurityContextAsyncTaskExecutor taskExecutorConfig(
          ThreadPoolTaskExecutor threadPoolTaskExecutor) {
    return new DelegatingSecurityContextAsyncTaskExecutor(threadPoolTaskExecutor);
  }
}