package com.htc.domain.repositories;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

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
  CompletableFuture<Either<Failure, Comment>> add(Comment comment);

  /**
   * Получение комментария по идентификатору.
   *
   * @param id идентификатор комментария
   * @return comment комментарий, подробнее {@link Comment}
   */
  CompletableFuture<Either<Failure, Comment>> get(int id);

  /**
   * Удаление комментария по идентификатору.
   *
   * @param id идентификатор комментария
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);
}
