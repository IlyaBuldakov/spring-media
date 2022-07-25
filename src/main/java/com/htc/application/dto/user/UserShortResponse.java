package com.htc.application.dto.user;

import com.htc.domain.entities.User;

/**
 * Краткое представление сущности пользователя (ответ на запрос).
 * @param id Идентификатор пользователя.
 * @param name Имя пользователя.
 */
public record UserShortResponse(int id, String name) {

  /**
   * Создаёт экземпляр класса {@link UserShortResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserShortResponse(User user) {
    this(user.getId().getValue(), user.getName().getValue());
  }
}
