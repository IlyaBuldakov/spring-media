package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий задач.
 */
public interface TaskRepository {
  /**
   * Создаёт задачу.
   *
   * @param task Задача.
   */
  Future<Either<Failure, Task>> create(Task task);

  /**
   * Обновляет данные задачи.
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
   * @return Список всех задач.
   */
  Future<Either<Failure, Iterable<Task>>> getAll();
}
