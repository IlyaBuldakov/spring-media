package com.htc.domain.repositories;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository {

  /**
   * Создаёт комментарий.
   *
   * @param comment Комментарий.
   */
  Future<Either<Failure, Comment>> create(Comment comment);

  /**
   * Получает комментарии по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @return Комментарии по идентификатору задачи.
   */
  Future<Either<Failure, Iterable<Comment>>> getCommentsByTaskId(int taskId);
}
