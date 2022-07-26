package com.htc.config;

import com.htc.application.filters.AuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Конфигурация Spring Security.
 */
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Фильтр, обрабатывающий Authentication Header запроса
     */
    private final AuthorizationFilter authorizationFilter;

    /**
     * URL выхода.
     */
    private final String logoutUrl = "/api/auth/logout";

    /**
     * Массив путей, не подлежащих авторизации.
     */
    private final String[] permittedPaths = {
            "/api/auth/login",
            "/api/auth/access-token"
    };

    /**
     * Конфигурация HttpSecurity.
     *
     * @param http HttpSecurity.
     */
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
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
                .addFilterAfter(this.authenticationFilter, BasicAuthenticationFilter.class);
    }

    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(
                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}