package com.htc.domain.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий контента.
 */
public interface ContentRepository {
  /**
   * Загружает контент в задачу.
   *
   * @param type Тип контента.
   * @param name Название контента.
   * @param dateCreate Дата создания контента.
   * @param authorId Идентификатор автора контента.
   * @param format Формат контента.
   * @param urlContent Путь к контенту.
   * @param urlPreview Путь к превью контента.
   * @param taskId Идентификатор задачи содержащей контент.
   * @return Контент или Ошибка.
   */
  CompletableFuture<Either<Failure, Content>> upload(
      Content.Type type,
      String name,
      LocalDateTime dateCreate,
      int authorId,
      Content.Format format,
      String urlContent,
      String urlPreview,
      int taskId,
      boolean approve
  );

  /**
   * Получает контент.
   *
   * @param id Идентификатор контента.
   * @return Контент.
   */
  CompletableFuture<Either<Failure, Content>> get(Id id);

  /**
   * Удаляет контент.
   *
   * @param id Идентификатор контента.
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получает содержимое ленты контента.
   *
   * @return Весь контент.
   */
  CompletableFuture<Either<Failure, Collection<Content>>> getContentFeed(boolean approve);
}
