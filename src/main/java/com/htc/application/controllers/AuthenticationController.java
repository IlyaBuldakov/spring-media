package com.htc.application.controllers;


import com.htc.application.dto.authentication.AccessTokenRequest;
import com.htc.application.dto.authentication.LoginRequest;
import com.htc.application.dto.authentication.LoginResponse;
import com.htc.application.services.TokenService;
import com.htc.domain.entities.User;
import com.htc.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с аутентификацией.
 */
@RestController
@RequestMapping("api/auth")
@Tags(@Tag(name = "Аутентификация"))
@AllArgsConstructor
public class AuthenticationController {
  private TokenService tokenService;

  private UserService userService;

  /**
   * Аутентифицирует пользователя.
   *
   * @param loginRequest Представление запроса на вход в систему.
   * @return Представление Access- и Refresh- токенов или ошибка.
   */
  @PostMapping(path = "/login")
  @Operation(summary = "Войти в систему.")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    // TODO: Изменить обработку ошибок для атрибутов.
    var email = User.Email.create(loginRequest.email()).get();
    var password = User.Password.create(loginRequest.password()).get();

    var user = this.userService
        .get(email, password)
        .getOrElseThrow(() -> new RuntimeException());

    // TODO: Обрабатывать ошибки так же как и в контроллере пользователей.
    return this.tokenService
        .createTokens(user.id(), user.role())
        .getOrElseThrow(() -> new RuntimeException());
  }

  /**
   * Получает новый Access-токен.
   *
   * @param accessTokenRequest Представление запроса на обновление Access-токена.
   * @return Представление токенов или ошибка.
   */
  @PostMapping(path = "/access-token")
  @Operation(summary = "Получить новый Access-токен.")
  public LoginResponse accessToken(@RequestBody AccessTokenRequest accessTokenRequest) {
    // TODO: Реализовать метод.
    return null;
  }
}