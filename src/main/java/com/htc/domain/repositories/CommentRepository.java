package com.htc.domain.repositories;

import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository {
  /**
   * Созает комментарий.
   *
   * @param comment Комментарий.
   */

  CompletableFuture<Either<Failure, Comment>> create(Comment comment);

  /**
   * Обновляет комментарий.
   *
   * @param comment Комментарий.
   */
  CompletableFuture<Either<Failure, Comment>> update(Comment comment);

  /**
   * Удаляет комментарий.
   *
   * @param id Идентификатор контента.
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получает комментарий.
   *
   * @param id Идентификатор комментария.
   * @return Комментарий.
   */
  CompletableFuture<Either<Failure, Comment>> get(int id);

  /**
   * Получает список всего комментариев.
   *
   * @return Список комментарев.
   */
  CompletableFuture<Either<Failure, Iterable<Comment>>> getAll();
}
