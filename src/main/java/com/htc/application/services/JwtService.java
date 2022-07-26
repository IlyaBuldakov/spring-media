package com.htc.application.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.htc.application.dto.login.LoginResponse;
import com.htc.domain.entities.user.Role;

/**
 * Интерфейс, описывающий базовые операции для работы с JWT токеном.
 */
public interface JwtService {

    /**
     * Создание JWT токена.
     *
     * @param id Идентификатор пользователя.
     * @param role Роль пользователя.
     * @param lifeTime Время жизни токена.
     * @return Токен.
     */
    String createJwtToken(int id, Role role, String email, int lifeTime);

    /**
     * Создание пары токенов (access, refresh).
     *
     * @param id Идентификатор пользователя.
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
     * Декодирование JWT токена.
     *
     * @param token JWT токен.
     * @return DecodedJWT.
     */
    DecodedJWT decodeToken(String token);

    /**
     * Проверка JWT токена.
     *
     * @param token JWT токен.
     * @return boolean.
     */
    boolean isTokenValid(String token);
}
