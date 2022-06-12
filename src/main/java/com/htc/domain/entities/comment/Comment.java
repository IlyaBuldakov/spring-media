package com.htc.domain.entities.comment;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDate;
import lombok.Getter;

/**
 * Комментарий.
 */
public class Comment {

  /**
   *  Идентификатор комментария.
   *
   * @return id Идентификатор комментария.
   */
  private @Getter int id;

  /**
   *  Дата создания комментария.
   *
   * @return date Дата создания комментария.
   */
  private @Getter LocalDate date;

  /**
   *  Пользователь создавший комментарий.
   *
   * @return user Пользователь создавший комментарий, см. {@Link User}
   */
  private @Getter User user;

  /**
   *  Текст комментария.
   *
   * @return message Текст комментария.
   */
  private @Getter String message;

  /**
   *  Идентификатор задачи, в которой оставлен комментарий.
   *
   * @return taskId Идентификатор задачи, в которой оставлен комментарий.
   */
  private @Getter int taskId;

  /**
   * Создает комментарий к задаче.
   *
   * @param id Идентификатор комментария.
   * @param date Дата создания комментария.
   * @param user Автор комментария.
   * @param message Текст комментария.
   * @param taskId Идентификатор задачи с комментарием.
   * @return Комментарий.
   */
  public static Either<Failure, Comment> create(
      int id, LocalDate date, User user, String message, int taskId) {

    var comment = new Comment();
    comment.id = id;
    comment.date = date;
    comment.user = user;
    comment.message = message;
    comment.taskId = taskId;
    return Either.right(comment);
  }

  public static Either<Failure, Comment> getCommentByTaskId() {

    return null;
  }
}
