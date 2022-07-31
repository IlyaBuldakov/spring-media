package com.htc.application.filters;

import com.htc.application.services.AuthorizeRole;
import com.htc.application.services.TokenService;
import com.htc.application.services.UserAuthenticationToken;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.utility.parameters.Id;
import java.io.IOException;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Фильтр запросов, производящий аутентификацию пользователей.
 */
@Component
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserAuthenticationFilter extends OncePerRequestFilter {
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    var header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header != null && !header.isEmpty()) {
      var token = this.tokenService.getToken(header);
      if (this.tokenService.isTokenValid(token)) {
        var jwt = this.tokenService.parseToken(token);
        var id = jwt.getClaim("id").asLong();
        var role = jwt.getClaim("role").asString();
        var setRole = Set.of(new AuthorizeRole(Role.valueOf(role)));
        var userAuthentication = new UserAuthenticationToken(Id.create(id).get(), setRole, true);
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
