package finalproject.configuration;

import finalproject.application.services.auth.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * Конфигурация Spring Security.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  private final JwtFilter jwtFilter;

  /** Настройка цепи фильтров безопасности.
   *
   * @param http объект HttpSecurty
   * @return  SecurityFilterChain цепь фильтров безопасности
   * @throws Exception Возникающее исключение
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(
                    auth -> {
                      auth
                                .antMatchers("/api/auth/login", "/api/auth/refresh-token", "/swagger-ui/**",
                                        "/v2/api-docs", "/swagger-resources/**")
                                  .permitAll()
                                .antMatchers("/content/*", "/files/*")
                                  .hasAnyAuthority("ADMIN", "CONTENT_MAKER", "MANAGER")
                                .anyRequest()
                                  .authenticated()
                                .and()
                                .addFilterAfter(jwtFilter,
                                        UsernamePasswordAuthenticationFilter.class);
                    }

            )
            .authorizeRequests().and().logout()
              .logoutUrl("/api/auth/logout")
              .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
              .permitAll()
            .and()
            .build();
  }

}