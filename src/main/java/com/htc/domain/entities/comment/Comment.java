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
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int id;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String dateCreated;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора}.
   *
   *
   * @return id идентификатор автора
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int authorId;

  /**
   * Текст комментария.
   *
   * @return message текст комментария
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String message;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int taskId;
}
