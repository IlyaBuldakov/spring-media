package finalproject.application.services.auth;


import finalproject.domain.entities.user.Role;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Фильтр безопасности.
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {


  private final JwtProvider jwtProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {
    final String token = getTokenFromRequest((HttpServletRequest) request);
    if (token != null && jwtProvider.validateAccessToken(token)) {
      final Claims claims = jwtProvider.getAccessClaims(token);
      final JwtAuthentication jwtToken = new JwtAuthentication();
      jwtToken.setRoles(Collections.singleton(new AuthRole(
              Role.valueOf(claims.get("role", String.class)))));
      jwtToken.setUserId(claims.get("id", Integer.class));
      jwtToken.setEmail(claims.getSubject());
      jwtToken.setAuthenticated(true);
      SecurityContextHolder.getContext().setAuthentication(jwtToken);
    }
    fc.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    final String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

}

