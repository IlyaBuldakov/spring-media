package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий задачи.
 */
public interface TaskRepository {
  /**
   * Добавление новой задачи.
   *
   * @param task новая задача
   *
   * @return task новая задача, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Task>> add(Task task);

  /**
   * Получение задачи по идентификатору.
   *
   * @param id идентификатор задачи
   *
   * @return task задача, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Task>> get(int id);

  /**
   * Получение списка задач.
   *
   * @return list список задач, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Iterable<Task>>> getAll();

  /**
   * Изменение задачи.
   *
   * @param task задача
   *
   * @return task задача, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Task>> update(Task task);

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id идентификатор задачи
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);
}
