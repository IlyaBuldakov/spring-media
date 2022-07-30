package com.htc.application.dto.content;

import com.htc.application.dto.BaseResponse;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Content.ContentFormat;
import com.htc.domain.entities.content.ContentType;
import java.time.LocalDate;
import lombok.Getter;

/**
 * Представление контента (ответ).
 */
public class ContentResponse implements BaseResponse {

  /**
   * Конструктор из соответствующей сущности.
   *
   * @param content Сущность {@link Content контента}.
   */
  public ContentResponse(Content content) {
    this.id = content.getId();
    this.type = content.getType();
    this.dateCreated = content.getDateCreated();
    this.author = new UserShortResponse(content.getAuthor());
    this.contentFormat = content.getFormat();
    this.url = content.getUrl();
    this.preview = content.getPreview();
  }

  /**
   * Идентификатор контента.
   */
  private final @Getter int id;

  /**
   * Тип контента.
   */
  private final @Getter ContentType type;

  /**
   * Дата создания контента.
   */
  private final @Getter LocalDate dateCreated;

  /**
   * Автор контента.
   */
  private final @Getter UserShortResponse author;

  /**
   * Формат контента.
   */
  private final @Getter ContentFormat contentFormat;

  /**
   * URL контента на сервере.
   */
  private final @Getter String url;

  /**
   * Preview контента на сервере.
   */
  private final @Getter String preview;
}