package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.Comment;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
  @NotNull
  private @Getter LocalDateTime date;

  @ManyToOne
  @NotNull
  private @Getter UserModel user;

  @ManyToOne
  @NotNull
  private @Getter TaskModel task;

  @NotNull
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
