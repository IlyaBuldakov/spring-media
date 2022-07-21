package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.Collection;
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
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает контент.
   *
   * @param id Идентификатор контента.
   * @return Контент.
   */
  CompletableFuture<Either<Failure, Content>> get(Id id);

  /**
   * Получает список всего контента.
   *
   * @return Список контента.
   */
  CompletableFuture<Either<Failure, Collection<Content>>> getAll();
}
