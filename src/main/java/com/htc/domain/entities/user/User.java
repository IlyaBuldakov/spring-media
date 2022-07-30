package com.htc.domain.entities.user;

import com.htc.domain.entities.ResponseConvertable;

/**
 * Интерфейс, описывающий пользователя.
 */
public interface User extends ResponseConvertable {

  /**
   * Идентификатор пользователя.
   *
   * @return Идентификатор пользователя.
   */
  Integer getId();

  /**
   * Имя пользователя.
   *
   * @return Имя пользователя.
   */
  String getName();

  /**
   * Пароль пользователя.
   * Требования к паролю:
   * 1. Длина пароля от 8 до 20 символов (включительно),
   * 2. Пароль должен содержать символы латинского алфавита обоих рег-ров,
   * 3. Пароль должен содержать хотя бы одну цифру,
   * 4. Пароль может содержать знак подчёркивания.
   * Корректный пароль: 1aaAbbBccC
   * Некорректный пароль: aaa
   *
   * @return Пароль пользователя.
   */
  String getPassword();

  /**
   * E-mail пользователя.
   *
   * @return E-mail пользователя.
   */
  String getEmail();

  /**
   * Аватар пользователя.
   *
   * @return Аватар пользователя.
   */
  String getAvatar();

  /**
   * Роль пользователя.
   *
   * @return Роль пользователя.
   */
  Role getRole();
}
