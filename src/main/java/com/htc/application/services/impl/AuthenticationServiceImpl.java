package com.htc.application.services.impl;

import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.errors.UnauthorizedResponse;
import com.htc.application.dto.login.LoginRequest;
import com.htc.application.dto.login.LoginResponse;
import com.htc.application.security.UserDetailsServiceImpl;
import com.htc.application.services.AuthenticationService;
import com.htc.application.services.JwtService;
import com.htc.application.services.UsersService;
import com.htc.domain.entities.failures.Unauthorized;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса, проводящего аутентификацию.
 */
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  JwtService jwtService;
  UsersService usersService;
  AuthenticationManager authenticationManager;

  /**
   * Основной метод проведения аутентификации в приложении.
   * Используется AuthenticationManager по умолчанию, делегирующий работу получения пользователя
   * {@link DaoAuthenticationProvider провайдеру}, который в свою очередь
   * использует {@link UserDetailsServiceImpl реализацию}
   * {@link UserDetailsService} для того, чтобы Spring Security смог получить пользователя
   * из базы данных и сверить его данные со входящими.
   *
   * @param loginRequest E-mail, пароль.
   * @return Access-токен, refresh-токен.
   */
  @Override
  public CompletableFuture<LoginResponse> login(LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequest.getEmail(),
                      loginRequest.getPassword()
              ));
      var user = usersService.getByEmail(loginRequest.getEmail()).get();
      return CompletableFuture.completedFuture(
              jwtService.createPairOfTokens(user.getId(), user.getRole().getRole(), user.getEmail())
      );

    } catch (AuthenticationException exception) {
      throw new UnauthorizedResponse(Unauthorized.BAD_CREDENTIALS.getMessage());
    } catch (ExecutionException | InterruptedException e) {
      throw new InternalServerErrorResponse();
    }
  }

  @Override
  public CompletableFuture<LoginResponse> getNewAccessToken(String refreshToken) {
    return CompletableFuture.completedFuture(jwtService.getNewAccessToken(refreshToken));
  }
}