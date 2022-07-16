package com.htc.infrastructure.models;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Модель комментария для СУБД.
 */
@Entity
@Table(name = "Comments")
public class CommentModel implements Comment {
  /**
   * Идентификатор комментария.
   */
  @javax.persistence.Id
  @GeneratedValue
  private Integer id;

  @Override
  public Id getId() {
    return Id.create(this.id).get();
  }

  /**
   * Дата создания комментария.
   */
  private LocalDateTime dateCreateComment;

  @Override
  public LocalDateTime getDateCreateComment() {
    return dateCreateComment;
  }

  /**
   * Пользователь создавший комментарий.
   */
  private int userId;

  @Override
  public int getUserId() {
    return userId;
  }

  /**
   * Сообщение комментария.
   */
  private String commentMessage;

  @Override
  public String getCommentMessage() {
    return commentMessage;
  }

  /**
   * Задача в которой создан комментарий.
   */
  private int taskId;

  @Override
  public int taskId() {
    return taskId;
  }

  protected CommentModel() {}

  public CommentModel(
      LocalDateTime dateCreateComment,
      int userId,
      int taskId,
      String commentMessage) {
    this(Id.create(0).get(), dateCreateComment, userId, taskId, commentMessage);
  }

  /**
   * Создаёт экземпляр класса {@link CommentModel}.
   *
   * @param id Идентификатор комментария.
   * @param dateCreateComment Дата создания комментария.
   * @param userId Пользователь создавший комментарий.
   * @param taskId Задача в которой создан комментарий.
   * @param commentMessage Сообщение комментария.
   */
  public CommentModel(
      Id id,
      LocalDateTime dateCreateComment,
      int userId,
      int taskId,
      String commentMessage) {
    this.id = id.getValue();
    this.dateCreateComment = dateCreateComment;
    this.userId = userId;
    this.taskId = taskId;
    this.commentMessage = commentMessage;
  }
}
