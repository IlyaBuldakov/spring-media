package com.htc.application.controllers;

import com.htc.application.dto.authentication.AccessTokenRequest;
import com.htc.application.dto.authentication.LoginRequest;
import com.htc.application.dto.authentication.LoginResponse;
import com.htc.application.services.TokenService;
import com.htc.domain.usecases.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с аутентификацией.
 */
@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@Tags(@Tag(name = "Аутентификация"))
public class AuthenticationController {
  private TokenService tokenService;
  private UserUseCase.GetUserByEmail getUserByEmail;

  @Async
  @PostMapping(path = "/login")
  @Operation(summary = "Войти в систему.")
  public CompletableFuture<LoginResponse> login(@RequestBody LoginRequest loginRequest)
          throws ExecutionException, InterruptedException {
    var params = new UserUseCase.GetUserByEmail.Params(loginRequest.getEmail(), "email");
    // TODO: Проверить пароль.

    var user = this.getUserByEmail.execute(params)
            .get()
            .get();

    var response = this.tokenService.createTokens(
            user.getId(),
            user.getRole());

    return CompletableFuture.completedFuture(response);
  }

  @Async
  @PostMapping(path = "/access-token")
  @Operation(summary = "Получить новый Access-токен.")
  public CompletableFuture<LoginResponse> accessToken(
          @RequestBody AccessTokenRequest accessTokenRequest) {
    return null;
  }
}