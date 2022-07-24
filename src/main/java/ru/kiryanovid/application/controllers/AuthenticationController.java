package ru.kiryanovid.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kiryanovid.application.dto.authentication.AuthLoginResponseDto;

/**
 * Контроллер аутентификации.
 */
@RestController
@RequestMapping("/api/auth")
@Tags(@Tag(name = "Аутентификация"))
@AllArgsConstructor
public class AuthenticationController {
  @PostMapping(path = "/login")
  @Async
  @Operation(summary = "Войти в систему")
  public CompletableFuture<AuthLoginResponseDto> login() {
    return null;
  }

  @PostMapping(path = "/logout")
  @Async
  @SecurityRequirement(name = "JWT")
  @Operation(summary = "Выйти в систему")
  public CompletableFuture<AuthLoginResponseDto> logout() {
    return null;
  }

  @PostMapping(path = "/access-token")
  @Async
  @Operation(summary = "Получить новый Access-токе")
  public CompletableFuture<AuthLoginResponseDto> accessToken() {
    return null;
  }
}
