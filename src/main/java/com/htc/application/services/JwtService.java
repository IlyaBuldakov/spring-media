package com.htc.application.services;

import com.htc.application.dto.login.LoginResponse;
import com.htc.domain.entities.user.Role;
import org.springframework.security.core.Authentication;

/**
 * Интерфейс, описывающий базовые операции для работы с JWT токеном.
 */
public interface JwtService {

  /**
   * Создание JWT токена.
   *
   * @param id       Идентификатор пользователя.
   * @param role     Роль пользователя.
   * @param lifeTime Время жизни токена.
   * @return Токен.
   */
  String createJwtToken(int id, Role role, String email, int lifeTime);

  /**
   * Создание пары токенов (access, refresh).
   *
   * @param id   Идентификатор пользователя.
   * @param role Роль пользователя.
   * @return LoginResponse.
   */
  LoginResponse createPairOfTokens(int id, Role role, String email);

  /**
   * Получение токена из Authentication header (без типа аутентификации).
   *
   * @return JWT токен.
   */
  String getTokenFromHeader(String header);

  /**
   * Проверка JWT токена.
   *
   * @param token JWT токен.
   * @return boolean.
   */
  boolean isTokenValid(String token);

  /**
   * Получение Authentication из JWT токена.
   *
   * @param token JWT токен.
   * @return Authentication.
   */
  Authentication getAuthentication(String token);

  /**
   * Получение новго access-токена по refresh-токену.
   *
   * @param refreshToken Refresh-токен.
   * @return LoginResponse.
   */
  LoginResponse getNewAccessToken(String refreshToken);
}
