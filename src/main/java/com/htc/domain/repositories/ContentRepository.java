package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий медиаконтента.
 */
public interface ContentRepository {
  /**
   * Созает медиаконтент.
   *
   * @param content Медиаконтент.
   */

  CompletableFuture<Either<Failure, Content>> create(Content content);

  /**
   * Удаляет медиаконтент.
   *
   * @param id Идентификатор медиаконтент.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает содержимое ленты медиаконтента по запросу.
   *
   * @param page   Номер страницы.
   * @param count  Количество медиаконтента на страницу.
   * @param author Имя автора медиаконтента (запрос).
   * @param date   Дата публикации медиаконтента (запрос).
   * @param typeId Идентификатор тип медиаконтента (запрос).
   * @return Список медиаконтента или ошибку.
   */
  CompletableFuture<Either<Failure, Collection<Content>>> findByQuery(
          Integer page,
          Integer count,
          String author,
          LocalDate date,
          Integer typeId);

  /**
   * Получает список медиакнтета относящегося к одной задаче.
   *
   * @param taskId Идентификатор задачи.
   * @return Список контента или ошибку.
   */
  CompletableFuture<Either<Failure, Collection<Content>>> findByTask(Id taskId);
}
