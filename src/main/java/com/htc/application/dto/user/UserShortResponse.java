package com.htc.application.dto.user;

import com.htc.domain.entities.Entity;
import com.htc.domain.entities.User;

/**
 * Краткое представление сущности пользователя (ответ на запрос).
 * @param id Идентификатор пользователя.
 * @param name Имя пользователя.
 */
public record UserShortResponse(int id, String name)
        implements Entity.View<UserShortResponse, User> {
  @Override
  public UserShortResponse fromEntity(User user) {
    return new UserShortResponse(user);
  }

  /**
   * Создаёт экземпляр класса {@link UserShortResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserShortResponse(User user) {
    this(user.id().getValue(), user.name().getValue());
  }
}
