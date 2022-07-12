package com.htc.application.dto.user;

import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Краткое представление сущности пользователя (ответ на запрос).
 */
public class UserShortResponse {
  /**
   * Идентификатор пользователя.
   *
   * @return id Идентификатор пользователя.
   */
  private final @Getter int id;

  /**
   * Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link UserShortResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserShortResponse(User user) {
    this.id = user.getId().getValue();
    this.name = user.getName().getValue();
  }
}
