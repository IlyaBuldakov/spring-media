package com.htc.application.controllers;

import com.htc.application.dto.authentication.AccessTokenRequest;
import com.htc.application.dto.authentication.LoginRequest;
import com.htc.application.dto.authentication.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
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
  @PostMapping(path = "/login")
  @Async
  @Operation(summary = "Войти в систему.")
  public CompletableFuture<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    return null;
  }

  @PostMapping(path = "/logout")
  @Async
  @SecurityRequirement(name = "JWT")
  @Operation(summary = "Выйти из системы.")
  public void logout() {
  }

  @PostMapping(path = "/access-token")
  @Async
  @Operation(summary = "Получить новый Access-токен.")
  public CompletableFuture<LoginResponse> accessToken(
          @RequestBody AccessTokenRequest accessTokenRequest) {
    return null;
  }
}