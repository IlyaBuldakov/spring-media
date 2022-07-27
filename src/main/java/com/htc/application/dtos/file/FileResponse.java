package com.htc.application.dtos.file;

import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности файла.
 */
@EqualsAndHashCode
public class FileResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Long id;

  /**
   * Имя.
   *
   * @return name имя
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
   * Формат.
   *
   * @see Format
   *
   * @return format формат
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Format format;

  /**
   * Путь.
   *
   * @return fileUrlPath путь
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String fileUrlPath;

  /**
   * Содержимое в base64.
   *
   * @return file содержимое
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String file;

  /**
   * Создание основного представления файла.
   *
   * @param file сущность файла, подробнее {@link File}
   */
  public FileResponse(File file) {
    this.id = file.getId().getValue();
    this.name = file.getName().getValue();
    this.dateCreated = file.getDateCreated().getValue();
    this.format = file.getFormat();
    this.fileUrlPath = file.getFileUrlPath().getValue();
    this.file = file.getFile().getValue();
  }
}
