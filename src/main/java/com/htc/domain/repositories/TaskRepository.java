package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.Task;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий задач.
 */
public interface TaskRepository {

  /**
   * Созает задачу.
   *
   * @param name Название задачи.
   * @param contentType Тип медиаконтекта.
   * @param description Описание задачи.
   * @param authorId Автор задачи.
   * @param executorId Исполнитель задачи.
   * @param dateExpired Срок задачи.
   * @return Задача или ошибка
   */
  CompletableFuture<Either<Failure, Task>> create(
          Task.Name name,
          Content.Type contentType,
          Task.Description description,
          Id authorId,
          Id executorId,
          LocalDateTime dateExpired);

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип медиаконтекта.
   * @param description Описание задачи
   * @param authorId Автор задачи.
   * @param executorId Исполнитель задачи.
   * @param dateExpired Срок задачи.
   * @return Задача или ошибка
   */
  CompletableFuture<Either<Failure, Task>> update(
          Id id,
          Task.Name name,
          Content.Type contentType,
          Task.Description description,
          Id authorId,
          Id executorId,
          LocalDateTime dateExpired);

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает задачу.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  CompletableFuture<Either<Failure, Task>> get(Id id);

  /**
   * Получает список всех задач.
   *
   * @return Список задач.
   */
  CompletableFuture<Either<Failure, Collection<Task>>> getAll();


}
