package com.htc.domain.entities.user;

import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserName;
import com.htc.domain.entities.utility.parameters.user.UserPassword;

/**
 * Пользователь.
 */
public interface User {
  /**
   * Получить идентификатор пользователя.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Получить полное имя пользователя.
   *
   * @see UserName#create(String)
   *
   * @return name полное имя пользователя
   */
  UserName getName();

  /**
   * Получить пароль.
   *
   * @see UserPassword#create(String)
   *
   * @return password пароль пользователя
   */
  UserPassword getPassword();

  /**
   * Получить электронную почту.
   *
   * @see UserEmail#create(String)
   *
   * @return email электронная почта пользователя
   */
  UserEmail getEmail();

  /**
   * Получить аватар в base64.
   *
   * @see UserImage#create(String)
   *
   * @return avatar аватар пользователя
   */
  UserImage getAvatar();

  /**
   * Получить роль пользователя.
   *
   * @see Role
   *
   * @return role роль
   */
  Role getRole();
}
