package com.htc.application.filters;

import java.io.IOException;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.htc.application.UserAuthentication;
import com.htc.application.services.TokenService;
import com.htc.domain.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationFilter implements Filter {
  private TokenService tokenService;

  @Override
  public void doFilter(
          ServletRequest servletRequest,
          ServletResponse servletResponse,
          FilterChain filterChain) throws IOException, ServletException {
    var request = (HttpServletRequest) servletRequest;
    var header = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (header != null && !header.isEmpty()) {
      var token = this.tokenService.getToken(header);

      if (this.tokenService.isTokenValid(token)) {
        var jwt = this.tokenService.parseToken(token);

        var id = jwt.getClaim("id").asInt();
        var role = jwt.getClaim("role").asString();
        var userRole = User.Role.valueOf(role);
        var authority = new UserAuthentication.UserGrantedAuthority(userRole);
        var userAuthentication = new UserAuthentication(id, Set.of(authority));

        SecurityContextHolder.getContext()
                .setAuthentication(userAuthentication);
      }
    }


    // Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NSIsIm5hbWUiOiJKb2huIEdvbGQiLCJhZG1pbiI6dHJ1ZX0K.LIHjWCBORSWMEibq-tnT8ue_deUqZx1K0XxCOXZRrBI

    filterChain.doFilter(servletRequest, servletResponse);
  }
}