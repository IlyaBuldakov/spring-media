package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
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
  private Integer commentId;

  @Override
  public Id getId() {
    return Id.create(commentId).get();
  }

  /**
   * Дата создания комментария.
   */
  private @Getter LocalDateTime date;

  @ManyToOne
  @JoinColumn(name = "comment_parent_user", referencedColumnName = "user_id", nullable = false)
  private @Getter UserModel user;

  @ManyToOne
  @JoinColumn(name = "comment_parent_task", referencedColumnName = "task_id", nullable = false)
  private @Getter TaskModel task;

  private String message;

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
