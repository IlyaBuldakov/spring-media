package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Модель комментариев для СУБД.
 */
@Entity
@Table(name = "Comments")
@AllArgsConstructor
public class CommentModel implements Comment {

  /**
   * Идентификатор комментария.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  private Integer commentId;

  /**
   * Дата создания комментария.
   */
  private @Getter LocalDateTime date;

  @ManyToOne
  private @Getter UserModel user;

  @ManyToOne
  private @Getter TaskModel task;

  private String message;

  @Override
  public Id getId() {
    return Id.create(commentId).get();
  }

  @Override
  public Message getMessage() {
    return Comment.Message.create(this.message).get();
  }

  protected CommentModel() {
  }

  /**
   * Создает модель комментария.
   *
   * @param user Модель пользователя.
   * @param task Модель задачи.
   * @param message Текст коментария.
   */
  public CommentModel(UserModel user, TaskModel task, Message message) {
    this.date = LocalDateTime.now();
    this.user = user;
    this.task = task;
    this.message = message.getValue();
  }
}
