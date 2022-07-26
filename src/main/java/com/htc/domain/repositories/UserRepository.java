package com.htc.domain.repositories;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import java.util.Collection;
import java.util.Optional;

/**
 * Репозиторий пользователей.
 */
public interface UserRepository {

  /**
   * Создаёт пользователя.
   *
   * @param name Имя пользователя.
   * @param email Электронная почта пользователя
   * @param password Пароль пользователя.
   * @param image Изображение пользователя.
   * @param role Роль пользователя.
   * @return Созданный пользователь.
   */
  User create(
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role
  );

  /**
   * Обновляет данные пользователя.
   *
   * @param id Идентификатор пользователя.
   * @param name Имя пользователя.
   * @param email Электронная почта пользователя.
   * @param password Пароль пользователя.
   * @param image Изображение пользователя.
   * @param role Роль пользователя.
   * @return Обновлённый пользователь.
   */
  User update(
          Id id,
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role
  );

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  void delete(Id id);

  /**
   * Получает пользователя по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь с запрошенным идентификатором.
   */
  Optional<User> get(Id id);

  /**
   * Получает пользователя по его электронной почте.
   *
   * @param email Электронная почта пользователя.
   * @return Пользователь с запрошенной электронной почтой.
   */
  Optional<User> get(User.Email email);

  /**
   * Получает список всех пользователей.
   *
   * @return Список всех пользователей.
   */
  Collection<User> getAll();

  /**
   * Проверяет, существует ли пользователь с указанным идентификатором.
   *
   * @param id Идентификатор пользователя.
   * @return Результат проверки.
   */
  boolean exists(Id id);

  /**
   * Проверяет, существует ли пользователь с указанной электронной почтой.
   *
   * @param email Электронная почта пользователя.
   * @return Результат проверки.
   */
  boolean exists(User.Email email);

}