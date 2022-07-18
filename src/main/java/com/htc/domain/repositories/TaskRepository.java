package com.htc.domain.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий задач.
 */
public interface TaskRepository {
  /**
   * Создаёт задачу.
   *
   * @param name Название задачи.
   * @param type Тип контента в задаче.
   * @param description Описание задачи.
   * @param fileId Прикрепленные к задаче файлы.
   * @param authorId Идентификатор автора задачи.
   * @param executorId Идентификатор испольнителя задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата окончания задачи.
   * @param contentsId Прикрепленный контент.
   * @param commentsId Прикрепленные комментарии к задаче.
   * @param status Статус задачи.
   * @return Задача или ошибка.
   */
  CompletableFuture<Either<Failure, Task>> create(
      String name,
      Content.Type type,
      String description,
      int[] fileId,
      int authorId,
      int executorId,
      LocalDateTime dateCreated,
      LocalDateTime dateExpired,
      int[] contentsId,
      int[] commentsId,
      Task.Status status
  );

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param type Тип контента в задаче.
   * @param description Описание задачи.
   * @param fileId Прикрепленные к задаче файлы.
   * @param authorId Идентификатор автора задачи.
   * @param executorId Идентификатор испольнителя задачи.
   * @param dateExpired Дата окончания задачи.
   * @param contentsId Прикрепленный контент.
   * @param commentsId Прикрепленные комментарии к задаче.
   * @param status Статус задачи.
   * @return Задача или ошибка.
   */
  CompletableFuture<Either<Failure, Task>> update(
      Id id,
      String name,
      Content.Type type,
      String description,
      int[] fileId,
      int authorId,
      int executorId,
      LocalDateTime dateExpired,
      int[] contentsId,
      int[] commentsId,
      Task.Status status
  );

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
   * @return Список всех задач.
   */
  CompletableFuture<Either<Failure, Collection<Task>>> getAll();
}
