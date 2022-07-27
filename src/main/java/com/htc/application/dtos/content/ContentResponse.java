package com.htc.application.dtos.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности контента.
 */
@EqualsAndHashCode
public class ContentResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Long id;

  /**
   * Тип.
   *
   * @return type тип
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Type type;

  /**
   * Наименование.
   *
   * @return String наименование
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String dateCreated;

  /**
   * Автор.
   *
   * @return author автор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter User author;

  /**
   * Формат.
   *
   * @return format формат
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Format format;

  /**
   * Путь до файла.
   *
   * @return fileUrlPath путь до файла
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String fileUrlPath;

  /**
   * Путь.
   *
   * @return previewPath путь
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String previewPath;

  /**
   * Создание основного представления контента.
   *
   * @param content сущность комментария, подробнее {@link Content}
   */
  public ContentResponse(Content content) {
    this.id = content.getId().getValue();
    this.type = content.getType();
    this.name = content.getName().getValue();
    this.dateCreated = content.getDateCreated().getValue();
    this.author = content.getAuthor();
    this.format = content.getFormat();
    this.fileUrlPath = content.getFile().getFileUrlPath().getValue();
    this.previewPath = content.getPreviewPath().getValue();
  }
}
