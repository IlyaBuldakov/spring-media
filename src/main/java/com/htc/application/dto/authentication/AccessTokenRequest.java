package com.htc.application.dto.authentication;

/**
 * Представление запроса на обновление Access-токена.
 *
 * @param refreshToken Refresh-токен.
 */
public record AccessTokenRequest(String refreshToken) {}