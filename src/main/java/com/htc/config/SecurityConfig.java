package com.htc.config;

import com.htc.application.filters.AuthenticationFilter;
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
  private final AuthenticationFilter authenticationFilter;

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
                    .antMatchers(this.permittedPaths).permitAll()
                    .anyRequest().authenticated())
            .logout(config -> config
                    .logoutUrl(this.logoutUrl).permitAll()
                    // Отключить перенаправление после выхода из системы.
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
            .addFilterAfter(
                    this.authenticationFilter,
                    BasicAuthenticationFilter.class)
        .build();
  }
}
