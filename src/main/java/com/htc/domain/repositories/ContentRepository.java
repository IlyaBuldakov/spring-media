package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий медиаконтента.
 */
public interface ContentRepository {
  /**
   * Созает медиаконтент.
   *
   * @param type Тип медиаконтента.
   * @param name Наимнование медиаконтента.
   * @param dateCreated Дата публикации
   * @param author Пльзователь - автор медиаконтента.
   * @param format Формат медиаконтента.
   * @param url Адрес медиаконтента.
   * @param preview Адрес миниатюры медиаконтента.
   * @return Медиаконтент или ошибка.
   */

  CompletableFuture<Either<Failure, Content>> create(
          Content.Type type,
          Content.Name name,
          LocalDateTime dateCreated,
          User author,
          Content.Format format,
          Content.Url url,
          Content.Url preview);

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
