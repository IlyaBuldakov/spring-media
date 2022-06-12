package com.htc.domain.entities.content;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Контент.
 */
public class Content {

  /**
   *  Идентификатор контента.
   *
   * @return id Идентификатор контента.
   */
  private @Getter int id;

  /**
   *  Тип контента.
   *
   * @return contentType Тип контента, см. {@Link ContentType}
   */
  private @Getter ContentType contentType;

  /**
   *  Название контента.
   *
   * @return name Название контента.
   */
  private @Getter String name;

  /**
   *  Дата создания контента.
   *
   * @return dateCreated Дата создания контента.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   *  Автор контента.
   *
   * @return author Автор контента, см. {@Link User}
   */
  private @Getter User author;

  /**
   *  Формат контента.
   *
   * @return format Формат контента, см. {@Link ContentFormat}
   */
  private @Getter ContentFormat format;

  /**
   *  Путь к файлу контента.
   *
   * @return url Путь к файлу контента.
   */
  private @Getter String urlContent;

  /**
   *  Путь к превью контента.
   *
   * @return preview Путь к превью контента.
   */
  private @Getter String urlPreview;

  /**
   *  Идентификатор задачи, к которой прикреплен контент.
   *
   * @return taskId Идентификатор задачи, к которой прикреплен контент.
   */
  private @Getter int taskId;

  /**
   * Загружает контент к задаче.
   *
   * @param id Идентификатор контента.
   * @param contentType Тип контента.
   * @param name Имя контента.
   * @param dateCreated Дата создания контента.
   * @param author Автор контента.
   * @param format Формат контента.
   * @param urlContent Путь к файлу контента.
   * @param urlPreview Путь к превью контента.
   * @param taskId Идентификатор задачи с контентом.
   * @return Контент.
   */
  public static Either<Failure, Content> upload(
      int id, ContentType contentType, String name, LocalDateTime dateCreated, User author,
      ContentFormat format, String urlContent, String urlPreview, int taskId) {

    var content = new Content();
    content.id = id;
    content.contentType = contentType;
    content.name = name;
    content.dateCreated = dateCreated;
    content.author = author;
    content.format = format;
    content.urlContent = urlContent;
    content.urlPreview = urlPreview;
    content.taskId = taskId;
    return Either.right(content);
  }
}
