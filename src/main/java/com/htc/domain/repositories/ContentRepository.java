package com.htc.domain.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

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
   * @return Созданый медиаконтент.
   */

  Content create(
      Task task,
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
  void delete(Id id);

  /**
   * Получает содержимое ленты медиаконтента по запросу.
   *
   * @param page Номер страницы.
   * @param count Количество медиаконтента на страницу.
   * @param author Имя автора медиаконтента (запрос).
   * @param date Дата публикации медиаконтента (запрос).
   * @param typeId Идентификатор тип медиаконтента (запрос).
   * @return Список медиаконтента или ошибку.
   */

  Collection<Content> getFeedByQuery(
      Integer page,
      Integer count,
      String author,
      LocalDate date,
      Integer typeId);

  /**
   * Получает список медиакнтета относящегося к одной задаче.
   *
   * @param task Задача задачи.
   * @return Список контента или ошибку.
   */
  Collection<Content> findByTask(Task task);


  /**
   * Проверяет, существует ли контент с указанным идентификатором.
   *
   * @param id Идентификатор контента.
   * @return Результат проверки.
   */
  boolean exists(Id id);
}
