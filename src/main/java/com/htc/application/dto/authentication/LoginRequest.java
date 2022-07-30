package com.htc.application.dto.authentication;

/**
 * Представление запроса на вход в систему.
 *
 * @param email Электронная почта пользователя.
 * @param password Пароль пользователя.
 */
public record LoginRequest(String email, String password) {}
