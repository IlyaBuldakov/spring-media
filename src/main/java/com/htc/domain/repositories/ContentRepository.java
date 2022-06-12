package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий контента.
 */
public interface ContentRepository {
  /**
   * Загружает контент в задачу.
   *
   * @param content Контент.
   */
  Future<Either<Failure, Content>> upload(Content content);

  /**
   * Удаляет контент.
   *
   * @param id Идентификатор контента.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает контент по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @return Контент.
   */
  Future<Either<Failure, Iterable<Content>>> getContentsByTaskId(int taskId);

  /**
   * Получает содержимое ленты контента.
   *
   * @return Весь контент.
   */
  Future<Either<Failure, Iterable<Content>>> getContentFeed();
}
