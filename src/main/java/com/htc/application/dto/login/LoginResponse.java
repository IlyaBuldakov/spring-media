package com.htc.application.dto.login;

import com.htc.application.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление логина (ответ).
 */
@AllArgsConstructor
public class LoginResponse implements BaseResponse {

  private @Getter String accessToken;

  private @Getter String refreshToken;
}
