package domain.repositories;

import domain.entities.content.Content;
import domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий контента.
 */
public interface ContentRepository {
  /**
   * Созает контент.
   *
   * @param content Контент.
   */

  Future<Either<Failure, Content>> create(Content content);

  /**
   * Обновляет контент.
   *
   * @param content Контент.
   */
  Future<Either<Failure, Content>> update(Content content);

  /**
   * Удаляет контент.
   *
   * @param id Идентификатор контента.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает контент.
   *
   * @param id Идентификатор контента.
   * @return Контент.
   */
  Future<Either<Failure, Content>> get(int id);

  /**
   * Получает список всего контента.
   *
   * @return Список контента.
   */
  Future<Either<Failure, Iterable<Content>>> getAll();
}
