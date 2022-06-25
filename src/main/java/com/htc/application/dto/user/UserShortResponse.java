package com.htc.application.dto.user;

import com.htc.application.dto.DataTransferObject;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Краткое представление сущности пользователя (ответ на запрос).
 */
public class UserShortResponse extends DataTransferObject<User> {


  private final @Getter int id;

  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link UserShortResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserShortResponse(User user) {
    super(user);

    this.id = user.getId();
    this.name = user.getName();
  }
}
