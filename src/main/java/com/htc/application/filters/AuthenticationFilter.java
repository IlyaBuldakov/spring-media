package com.htc.application.filters;

import com.htc.application.UserAuthenticationToken;
import com.htc.application.services.TokenService;
import com.htc.domain.entities.Id;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    var header = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (header != null && !header.isEmpty()) {
      var token = this.tokenService.getToken(header);

      if (this.tokenService.isTokenValid(token)) {
        var jwt = this.tokenService.parseToken(token);

        var id = jwt.getClaim("id").asInt();
        var userAuthentication = new UserAuthenticationToken(Id.create(id).get(), true);

        SecurityContextHolder.getContext()
                .setAuthentication(userAuthentication);
      }
    }

    filterChain.doFilter(request, response);
  }
}
