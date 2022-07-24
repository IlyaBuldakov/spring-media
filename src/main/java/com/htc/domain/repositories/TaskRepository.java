package com.htc.domain.repositories;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий задачи.
 */
public interface TaskRepository {
  //TODO включить в логику действия изменения элементов (удаление файла, замена пользователя)
  /**
   * Добавление новой задачи.
   *
   * @param name наименование
   * @param type тип
   * @param description описание
   * @param author автор
   * @param executor исполнитель
   * @param dateCreated дата создания
   * @param dateExpired дата выполнения
   * @return task новая задача, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Task>> add(FileName name, Type type, String description,
                                               User author, User executor, DateCreated dateCreated,
                                               DateCreated dateExpired);

  /**
   * Получение задачи по идентификатору.
   *
   * @param id идентификатор задачи
   * @return task задача, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Task>> get(Id id);

  /**
   * Получение списка задач.
   *
   * @return list список задач, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, List<Task>>> getAll();

  /**
   * Изменение задачи.
   *
   * @param id идентификатор задачи
   * @param name наименование
   * @param type тип
   * @param description описание
   * @param file файл
   * @param author автор
   * @param executor исполнитель
   * @param dateCreated дата создания
   * @param dateExpired дата выполнения
   * @param content контент
   * @param comment коммент
   * @param status статус
   * @return task измененная задача, подробнее {@link Task}
   */
  CompletableFuture<Either<Failure, Task>> update(Id id, FileName name, Type type,
                                                  String description, File file, User author,
                                                  User executor, DateCreated dateCreated,
                                                  DateCreated dateExpired, Content content,
                                                  Comment comment, Status status);

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id идентификатор задачи
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
