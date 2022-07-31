package com.htc.application.filters;

import com.htc.application.dto.errors.UnauthorizedResponse;
import com.htc.application.services.JwtService;
import com.htc.domain.entities.failure.Unauthorized;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Фильтр авторизации пользователей (прошедших аутентификацию).
 */
@Component
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

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
  public void doFilterInternal(
          @NonNull HttpServletRequest servletRequest,
          @NonNull HttpServletResponse servletResponse,
          @NonNull FilterChain filterChain) throws IOException, ServletException {
    var authHeader = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);

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