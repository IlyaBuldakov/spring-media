package com.htc.application.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление логина (ответ).
 */
@AllArgsConstructor
public class LoginResponse {

    private @Getter String accessToken;

    private @Getter String refreshToken;
}
