package com.htc.application.dtos.user;

import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Ответ. Краткое представление пользователя.
 */
public class UserShortResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Полное имя.
   *
   * @return name полное имя
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создание краткого представления пользователя.
   *
   * @param user сущность пользователя, подробнее {@link User}
   */
  public UserShortResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
  }
}
