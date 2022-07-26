package com.htc.application.filters;

import com.htc.application.security.RoleGrantedAuthority;
import com.htc.application.security.UserAuthentication;
import com.htc.application.services.JwtService;
import com.htc.domain.entities.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

@Component
@AllArgsConstructor
public class AuthorizationFilter extends GenericFilterBean {

    /**
     * Сервис для работы с JWT токеном.
     */
    JwtService jwtService;

    /**
     * Проверка Authentication Header.
     *
     * @param servletRequest Запрос.
     * @param servletResponse Ответ.
     * @param filterChain Цепочка фильтров.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null) {
            String token = jwtService.getTokenFromHeader(authHeader);
            if (jwtService.isTokenValid(token)) {
                var decodedToken = jwtService.decodeToken(token);
                var id = decodedToken.getClaim("id").asInt();
                var role = decodedToken.getClaim("role").asString();
                var userRole = Role.valueOf(role);
                var userAuthentication = new UserAuthentication(id, Set.of(
                        new RoleGrantedAuthority(userRole)
                ));
                SecurityContextHolder.getContext()
                        .setAuthentication(userAuthentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}