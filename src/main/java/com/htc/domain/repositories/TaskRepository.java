package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.tasks.Task;
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
   * @param files Файлы задачи.
   * @param authorId Автор задачи.
   * @param executorId Исполнитель задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Срок задачи.
   * @param contents Медиаконтент связанный с задачей
   * @param comments Комментарии задачи
   * @param taskStatus Статус задачи
   * @return Задача или ошибка
   */
  CompletableFuture<Either<Failure, Task>> create(
          Task.Name name,
          ContentType contentType,
          Task.Description description,
          Collection<File> files,
          Id authorId,
          Id executorId,
          LocalDateTime dateCreated,
          LocalDateTime dateExpired,
          Collection<Content> contents,
          Collection<Comment> comments,
          Task.TaskStatus taskStatus);

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип медиаконтекта.
   * @param description Описание задачи.
   * @param files Файлы задачи.
   * @param authorId Автор задачи.
   * @param executorId Исполнитель задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Срок задачи.
   * @param contents Медиаконтент связанный с задачей
   * @param comments Комментарии задачи
   * @param taskStatus Статус задачи
   * @return Задача или ошибка
   */
  CompletableFuture<Either<Failure, Task>> update(
          Id id,
          Task.Name name,
          ContentType contentType,
          Task.Description description,
          Collection<File> files,
          Id authorId,
          Id executorId,
          LocalDateTime dateCreated,
          LocalDateTime dateExpired,
          Collection<Content> contents,
          Collection<Comment> comments,
          Task.TaskStatus taskStatus);

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
