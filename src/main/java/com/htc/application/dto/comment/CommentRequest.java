package com.htc.application.dto.comment;

/**
 * Представление сущности пользователя (запрос).
 */
public class CommentRequest {

  /**
   * Идентификатор пользователя создавшего комментарий.
   */
  @SuppressWarnings("JavadocDeclaration")
  public int userId;

  /**
   * Идентификатор задачи содержащей комментарий.
   */
  @SuppressWarnings("JavadocDeclaration")
  public int taskId;

  /**
   * Сообщение комментария.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String commentMessage;
}
