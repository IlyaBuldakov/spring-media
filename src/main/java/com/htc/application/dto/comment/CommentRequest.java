package com.htc.application.dto.comment;

/**
 * Представление сущности пользователя (запрос).
 */
public class CommentRequest {

  /**
   * Идентификатор пользователя создавшего комментарий.
   *
   * @return Идентификатор пользователя создавшего комментарий.
   */
  public int userId;

  /**
   * Идентификатор задачи содержащей комментарий.
   *
   * @return Идентификатор задачи содержащей комментарий.
   */
  public int taskId;

  /**
   * Сообщение комментария.
   *
   * @return commentMessage Сообщение комментария.
   */
  public String commentMessage;
}
