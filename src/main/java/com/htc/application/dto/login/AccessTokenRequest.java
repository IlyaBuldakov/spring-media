package com.htc.application.dto.login;

import com.htc.application.dto.BaseResponse;
import lombok.Getter;

/**
 * Представление запроса на получение
 * нового access-токена.
 */
public class AccessTokenRequest implements BaseResponse {

  private @Getter String refreshToken;
}
