package com.htc.application.filters;

import com.htc.application.dto.errors.UnauthorizedResponse;
import com.htc.application.services.JwtService;
import com.htc.domain.entities.failure.Unauthorized;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Фильтр авторизации пользователей (прошедших аутентификацию).
 */
@Component
@AllArgsConstructor
public class AuthorizationFilter extends GenericFilterBean {

  /**
   * Сервис для работы с JWT токеном.
   */
  JwtService jwtService;

  /**
   * Проверка Authorization Header.
   *
   * @param servletRequest  Запрос.
   * @param servletResponse Ответ.
   * @param filterChain     Цепочка фильтров.
   */
  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    var request = (HttpServletRequest) servletRequest;
    var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authHeader != null) {
      String token = jwtService.getTokenFromHeader(authHeader);
      if (jwtService.isTokenValid(token)) {
        var userAuthentication = jwtService.getAuthentication(token);
        SecurityContextHolder.getContext()
                .setAuthentication(userAuthentication);
      } else {
        SecurityContextHolder.clearContext();
        throw new UnauthorizedResponse(Unauthorized.DEFAULT_MESSAGE.getMessage());
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}