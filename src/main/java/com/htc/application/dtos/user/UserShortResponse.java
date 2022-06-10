package com.htc.application.dtos.user;

import com.htc.application.dtos.DataTransferObject;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Ответ. Краткое представление пользователя.
 */
public class UserShortResponse extends DataTransferObject<User> {
  /**
   * Идентификатор.
   *
   * @return id - идентификатор
   */
  private final @Getter int id;

  /**
   * Полное имя.
   *
   * @return name - полное имя
   */
  private final @Getter String name;

  /**
   * Создание краткого представления пользователя.
   *
   * @param user - сущность пользователя, подробнее {@link User}
   */
  public UserShortResponse(User user) {
    super(user);
    this.id = user.getId();
    this.name = user.getName();
  }
}
