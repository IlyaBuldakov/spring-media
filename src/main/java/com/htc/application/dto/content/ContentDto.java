package com.htc.application.dto.content;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.content.Content;
import java.time.LocalDateTime;

/**
 * Представление сущности медиаконтента.
 *
 * @param id Индентификатор медиаконтента.
 * @param type Тип медиаконтента.
 * @param name Наименование медиаконтента.
 * @param dateCreated Дата загрузки контента.
 * @param author Пользователь - автор контента.
 * @param format Формат контента.
 * @param url Адресс контента.
 * @param preview Превью контента.
 */
public record ContentDto(int id,
                         ContentTypeDto type,
                         String name,
                         LocalDateTime dateCreated,
                         UserShortResponse author,
                         String format,
                         String url,
                         String preview) {

  /**
   * Создаёт экземпляр класса {@link ContentDto}.
   *
   * @param content Сущность медиаконтента.
   */
  public ContentDto(Content content) {
    this(content.getId().getValue(),
            new ContentTypeDto(content.getType()),
            content.getName().getValue(),
            content.getDateCreated(),
            new UserShortResponse(content.getAuthor()),
            content.getFormat().name(),
            content.getUrl().getValue(),
            content.getPreview().getValue());
  }
}