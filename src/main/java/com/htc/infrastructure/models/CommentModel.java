package com.htc.infrastructure.models;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.attributes.Id;
import com.htc.infrastructure.exception.InvalidDataException;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;


/**
 * Модель комментариев для СУБД.
 */
@javax.persistence.Entity
@Table(name = "Comments")
@AllArgsConstructor
public class CommentModel implements Entity.Model<Comment> {

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
  private LocalDateTime date;

  @ManyToOne
  @NotNull
  private UserModel user;

  @ManyToOne
  @NotNull
  private TaskModel task;

  @NotNull
  private String message;


  protected CommentModel() {
  }

  @Override
  public Comment toEntity() {
    final var id = Id
        .create(this.commentId)
        .getOrElseThrow(InvalidDataException::new);

    final var message = Comment.Message
        .create(this.message)
        .getOrElseThrow(InvalidDataException::new);

    final var user = this.user.toEntity();
    final var task = this.task.toEntity();

    return new Comment(id, date, user, task, message);
  }

  public CommentModel(LocalDateTime date, UserModel user, TaskModel task, String message) {
    this(0, date, user, task, message);
  }
}
