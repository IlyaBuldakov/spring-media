package com.htc.domain.repositories;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий для комментария.
 */
public interface CommentRepository {
  /**
   * Добавление нового комментария в задачу.
   *
   * @param comment новый комментарий
   *
   * @return comment новый комментарий, подробнее {@link Comment}
   */
  Future<Either<Failure, Comment>> add(Comment comment);
}