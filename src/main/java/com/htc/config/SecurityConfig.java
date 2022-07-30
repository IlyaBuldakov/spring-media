package com.htc.config;

import com.htc.application.filters.UserAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Конфигуратор Spring Security.
 */
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final UserAuthenticationFilter userAuthenticationFilter;

  private final String logoutUrl = "/api/auth/logout";

  private final String[] permittedPaths = {
      "/api/auth/login",
      "/api/auth/access-token",
      "/swagger-ui/**",
      "/v3/api-docs/**"
  };

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf().disable()
            .sessionManagement(config -> config
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeRequests(config -> config
                    .antMatchers(permittedPaths).permitAll()
                    .anyRequest().authenticated())
            .logout(config -> config
                    .logoutUrl(logoutUrl).permitAll()
                    // Отключить перенаправление после выхода из системы.
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
            .addFilterAfter(userAuthenticationFilter, BasicAuthenticationFilter.class)
            .build();
  }
}
