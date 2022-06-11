package com.htc.domain.entities.comment;

import lombok.Getter;

/**
 * Комментарий.
 */
public class Comment {
  /**
   * Идентификатор комментария.
   *
   * @return id идентификатор
   */
  private @Getter int id;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  private @Getter String dateCreated;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора}.
   *
   *
   * @return id идентификатор автора
   */
  private @Getter int authorId;

  /**
   * Текст комментария.
   *
   * @return message текст комментария
   */
  private @Getter String message;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  private @Getter int taskId;
}
