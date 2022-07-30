package com.htc.application.controllers;

import com.htc.application.dtos.authentication.AccessTokenRequest;
import com.htc.application.dtos.authentication.LoginRequest;
import com.htc.application.dtos.authentication.LoginResponse;
import com.htc.application.dtos.exceptions.NotFoundResponse;
import com.htc.application.services.TokenService;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.usecases.user.GetUserByEmail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер аутентификации.
 */
@AllArgsConstructor
@RestController
@Tags(@Tag(name = "Аутентификация"))
@RequestMapping("api/auth")
public class AuthenticationController {
  private GetUserByEmail getUserByEmail;
  private TokenService tokenService;

  /**
   * Вход в систему.
   *
   * @param loginRequest запрос на вход в систему
   * @return token access- и refresh-токен
   */
  @Operation(summary = "Войти в систему.")
  @PostMapping(path = "/login")
  public CompletableFuture<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    var email = UserEmail.create(loginRequest.getEmail()).get();
    var password = UserPassword.create(loginRequest.getPassword()).get();
    User user;
    try {
      user = getUserByEmail.execute(new GetUserByEmail.Params(email)).get()
              .getOrElseThrow(() -> new RuntimeException());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
    LoginResponse response;
    if (user.getPassword().equals(password)) {
      response = tokenService.createTokens(
                      user.getId(), user.getRole())
              .getOrElseThrow(() -> new RuntimeException());
    } else {
      throw new NotFoundResponse(NotFound.DEFAULT_MESSAGE);
    }
    return CompletableFuture.completedFuture(response);
  }

  @SecurityRequirement(name = "JWT")
  @Operation(summary = "Выйти из системы.")
  @PostMapping(path = "/logout")
  public void logout() {}

  @PostMapping(path = "/access-token")
  @Operation(summary = "Получить новый Access-токен.")
  public LoginResponse accessToken(@RequestBody AccessTokenRequest accessTokenRequest) {
    // TODO: Добавить реализацию
    return null;
  }
}
