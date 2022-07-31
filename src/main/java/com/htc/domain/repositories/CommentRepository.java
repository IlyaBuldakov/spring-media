package com.htc.domain.repositories;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository {

  /**
   * Создает комментарий.
   *
   * @param user Пользователь - автор комментария.
   * @param task Задача связанная с комментарием.
   * @param message Сообщение комментария.
   * @return Ошибка или комментарий.
   */
  Comment create(User user, Task task, LocalDateTime dateCreated, Comment.Message message);

  /**
   * Удаляет комментарий.
   *
   * @param id Идентификатор контента.
   */
  void delete(Id id);

  /**
   * Получает список всего комментариев в задаче.
   *
   * @return Список комментариев в задаче.
   */
  Collection<Comment> getAllByTask(Task task);

  /**
   * Проверяет, существует ли уведомление с указанным идентификатором.
   *
   * @param id Идентификатор уведомления.
   * @return Результат проверки.
   */
  boolean exists(Id id);
}
