package com.htc.application.dto.authentication;

/**
 * Представление Access- и Refresh- токенов (ответ на запрос).
 *
 * @param accessToken Access-токен.
 * @param refreshToken Refresh-токен.
 */
public record LoginResponse(String accessToken, String refreshToken) {}
