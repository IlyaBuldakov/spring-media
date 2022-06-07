package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий задач.
 */
public interface TaskRepository {
  /**
   * Созает задачу.
   *
   * @param task Задача.
   */
  Future<Either<Failure, Task>> create(Task task);

  /**
   * Обновляет задачу.
   *
   * @param task Задача.
   */
  Future<Either<Failure, Task>> update(Task task);

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает задачу.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  Future<Either<Failure, Task>> get(int id);

  /**
   * Получает список всех задач.
   *
   * @return Список задач.
   */
  Future<Either<Failure, Iterable<Task>>> getAll();

  /**
   * Получает список задач по названию
   * строке запроса {@code query}.
   *
   * @param query Строка запроса.
   * @return Список задач.
   */
  Future<Either<Failure, Iterable<Task>>> search(String query);

  /**
   * Получает список задачи название и ответственый пользователь которой
   * соответствуют строке запроса {@code query} и пользователю {@code user}.
   *
   * @param query Строка запроса.
   * @param user  Пользователя.
   * @return Список пользователей.
   */
  Future<Either<Failure, Iterable<Task>>> search(String query, User user);

}
