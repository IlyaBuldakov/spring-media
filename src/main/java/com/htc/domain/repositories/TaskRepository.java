package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.tasks.Task;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий задач.
 */
public interface TaskRepository {
  /**
   * Созает задачу.
   *
   * @param task Задача.
   */
  CompletableFuture<Either<Failure, Task>> create(Task task);

  /**
   * Обновляет задачу.
   *
   * @param task Задача.
   */
  CompletableFuture<Either<Failure, Task>> update(Task task);

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получает задачу.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  CompletableFuture<Either<Failure, Task>> get(int id);

  /**
   * Получает список всех задач.
   *
   * @return Список задач.
   */
  CompletableFuture<Either<Failure, Collection<Task>>> getAll();



}
