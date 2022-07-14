package finalproject.configuration;

import finalproject.application.services.auth.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  private final JwtFilter jwtFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(
                    authz -> authz
                            .antMatchers("/api/auth/login", "/api/auth/token", "/api/auth/refresh-token",
                                    "/grade/**", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs").permitAll()
                            .antMatchers("/content/*", "/files/*").hasAnyAuthority("ADMIN", "CONTENT_MAKER", "MANAGER")
                            .anyRequest().authenticated()
                            .and()
                            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            ).build();
  }

}