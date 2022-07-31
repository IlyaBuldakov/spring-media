package com.htc.application.dto.content;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.Content;
import com.htc.domain.entities.Entity;
import java.time.LocalDateTime;

/**
 * Представление сущности медиаконтента.
 *
 * @param id Идентификатор медиаконтента.
 * @param type Тип медиаконтента.
 * @param name Наименование медиаконтента.
 * @param dateCreated Дата загрузки контента.
 * @param author Пользователь - автор контента.
 * @param format Формат контента.
 * @param url Адрес контента.
 * @param preview Превью контента.
 */
public record ContentDto(int id,
                         ContentTypeDto type,
                         String name,
                         LocalDateTime dateCreated,
                         UserShortResponse author,
                         String format,
                         String url,
                         String preview)
    implements Entity.View<ContentDto, Content> {
  @Override
  public ContentDto fromEntity(Content content) {
    return new ContentDto(content);
  }


  /**
   * Создаёт экземпляр класса {@link ContentDto}.
   *
   * @param content Сущность медиаконтента.
   */
  public ContentDto(Content content) {
    this(content.id().getValue(),
        new ContentTypeDto(content.type()),
        content.name().getValue(),
        content.dateCreated(),
        new UserShortResponse(content.author()),
        content.format().name(),
        content.contentUrl().getValue(),
        content.previewUrl().getValue());
  }
}