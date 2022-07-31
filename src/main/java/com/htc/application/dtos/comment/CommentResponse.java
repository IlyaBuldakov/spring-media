package com.htc.application.dtos.comment;

import com.htc.application.dtos.task.TaskResponse;
import com.htc.application.dtos.user.UserShortResponse;
import com.htc.domain.entities.comment.Comment;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности комментария.
 */
@EqualsAndHashCode
public class CommentResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Long id;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String dateCreated;

  /**
   * Автор.
   *
   * @return author автор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserShortResponse author;

  /**
   * Текст.
   *
   * @return message текст
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;

  /**
   * Контент.
   *
   * @return content контент
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TaskResponse task;

  /**
   * Создание основного представления комментария.
   *
   * @param comment сущность комментария, подробнее {@link Comment}
   */
  public CommentResponse(Comment comment) {
    this.id = comment.getId().getValue();
    this.dateCreated = comment.getDateCreated().getValue();
    this.author = new UserShortResponse(comment.getAuthor());
    this.message = comment.getMessage();
    this.task = new TaskResponse(comment.getTask());
  }
}
