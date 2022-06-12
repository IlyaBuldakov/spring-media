package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий для контента.
 */
public interface ContentRepository {
  /**
   * Добавление нового контента в задачу.
   *
   * @param content новый контент
   *
   * @return content новый контент, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Content>> add(Content content);

  /**
   * Получение ленты контента.
   *
   * @return list лента контента, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Iterable<Content>>> getAll();

  /**
   * Удаление контента по идентификатору.
   *
   * @param id идентификатор контента
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);
}
