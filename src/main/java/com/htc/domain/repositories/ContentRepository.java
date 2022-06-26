package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий контента.
 */
public interface ContentRepository {
  /**
   * Созает контент.
   *
   * @param content Контент.
   */

  CompletableFuture<Either<Failure, Content>> create(Content content);

  /**
   * Обновляет контент.
   *
   * @param content Контент.
   */
  CompletableFuture<Either<Failure, Content>> update(Content content);

  /**
   * Удаляет контент.
   *
   * @param id Идентификатор контента.
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получает контент.
   *
   * @param id Идентификатор контента.
   * @return Контент.
   */
  CompletableFuture<Either<Failure, Content>> get(int id);

  /**
   * Получает список всего контента.
   *
   * @return Список контента.
   */
  CompletableFuture<Either<Failure, Iterable<Content>>> getAll();
}
