package domain.repositories;

import domain.entities.comments.Comment;
import domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository {
  /**
   * Созает комментарий.
   *
   * @param comment Комментарий.
   */

  Future<Either<Failure, Comment>> create(Comment comment);

  /**
   * Обновляет комментарий.
   *
   * @param comment Комментарий.
   */
  Future<Either<Failure, Comment>> update(Comment comment);

  /**
   * Удаляет комментарий.
   *
   * @param id Идентификатор контента.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает комментарий.
   *
   * @param id Идентификатор комментария.
   * @return Комментарий.
   */
  Future<Either<Failure, Comment>> get(int id);

  /**
   * Получает список всего комментариев.
   *
   * @return Список комментарев.
   */
  Future<Either<Failure, Iterable<Comment>>> getAll();
}
