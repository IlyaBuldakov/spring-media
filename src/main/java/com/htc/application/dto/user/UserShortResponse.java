package com.htc.application.dto.user;

import com.htc.application.dto.BaseResponse;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Краткое представление сущности пользователя (ответ).
 */
public class UserShortResponse implements BaseResponse {

  /**
   * Конструктор из соответствующей сущности.
   *
   * @param user Сущность {@link User пользователя}.
   */
  public UserShortResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
  }

  /**
   * Идентификатор пользователя.
   */
  private final @Getter int id;

  /**
   * Имя пользователя.
   */
  private final @Getter String name;
}
