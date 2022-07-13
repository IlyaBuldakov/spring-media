package com.htc.domain.repositories;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

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
   * @return Пользователь или ошибка.
   */
  CompletableFuture<Either<Failure, User>> create(
      User.Name name,
      User.Email email,
      User.Password password,
      User.Image image,
      User.Role role);

  /**
   * Обновляет данные пользователя.
   *
   * @param id Идентификатор пользователя.
   * @param name Имя пользователя.
   * @param email Электронная почта пользователя.
   * @param password Пароль пользователя.
   * @param image Изображение пользователя.
   * @param role Роль пользователя.
   * @return Пользователь или ошибка.
   */
  CompletableFuture<Either<Failure, User>> update(
      Id id,
      User.Name name,
      User.Email email,
      User.Password password,
      User.Image image,
      User.Role role);

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  CompletableFuture<Either<Failure, User>> get(Id id);

  /**
   * Получает список всех пользователей.
   *
   * @return Список пользователей.
   */
  CompletableFuture<Either<Failure, Collection<User>>> getAll();
}
