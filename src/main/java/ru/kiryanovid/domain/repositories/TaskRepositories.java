package ru.kiryanovid.domain.repositories;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;

/**
 * Репозиторий задач.
 */

public interface TaskRepositories {
  /**
   * Создаёт задачу.
   *
   * @param task Задача.
   */
  CompletableFuture<Either<Failure, Task>> create(Task task);

  /**
   * Обновляет задачу.
   *
   * @param task задача.
   */
  Future<Either<Failure, Task>> update(Task task);

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачу.
   */
  Future<Either<Failure, Void>> delete(Integer id);

  /**
   * Получает пользователя.
   *
   * @param id Идентификатор задачи.
   * @return задача.
   */
  CompletableFuture<Either<Failure, Task>> get(Integer id);

  /**
   * Получает список всех задач.
   *
   * @return Список задач.
   */
  CompletableFuture<Either<Failure, Iterable<Task>>> getAll();
}
