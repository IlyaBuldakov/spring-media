package com.htc.domain.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

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
   * @param author Автор задачи.
   * @param executor Исполнитель задачи.
   * @param dateCreated Дата создания здачи задачи.
   * @param dateExpired Срок задачи.
   * @param status Статус задачи.
   * @return Созданая задача
   */
  Task create(
          Task.Name name,
          Content.Type contentType,
          Task.Description description,
          User author,
          User executor,
          LocalDateTime dateCreated,
          LocalDateTime dateExpired,
          Task.Status status);

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип медиаконтекта.
   * @param description Описание задачи
   * @param author Автор задачи.
   * @param executor Исполнитель задачи.
   * @param dateExpired Срок задачи.
   * @return Обновленная задача
   */
  Task update(
          Id id,
          Task.Name name,
          Content.Type contentType,
          Task.Description description,
          User author,
          User executor,
          LocalDateTime dateExpired);

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  void delete(Id id);

  /**
   * Получает задачу.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  Optional<Task> get(Id id);

  /**
   * Получает список всех задач.
   *
   * @return Список задач.
   */
  Collection<Task> getAll();


  /**
   * Проверяет, существует ли задача с указанным идентификатором.
   *
   * @param id Идентификатор задачи.
   * @return Результат проверки.
   */
  boolean exists(Id id);
}
