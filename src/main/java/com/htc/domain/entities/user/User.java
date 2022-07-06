package com.htc.domain.entities.user;

import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.UserEmail;
import com.htc.domain.entities.utility.parameters.UserImage;
import com.htc.domain.entities.utility.parameters.UserName;
import com.htc.domain.entities.utility.parameters.UserPassword;

/**
 * Пользователь.
 */
public interface User {
  /**
   * Идентификатор.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Полное имя пользователя.
   *
   * @see UserName#create(String)
   *
   * @return name полное имя пользователя
   */
  UserName getName();

  /**
   * Пароль.
   *
   * @see UserPassword#create(String)
   *
   * @return password пароль пользователя
   */
  UserPassword getPassword();

  /**
   * Электронная почта.
   *
   * @see UserEmail#create(String)
   *
   * @return email электронная почта пользователя
   */
  UserEmail getEmail();

  /**
   * Аватар в base64.
   *
   * @see UserImage#create(String)
   *
   * @return avatar аватар пользователя
   */
  UserImage getAvatar();

  /**
   * Роль пользователя.
   *
   * @see Role
   *
   * @return role роль
   */
  Role getRole();
}
