package com.htc.application.dto.file;

import com.htc.domain.entities.File;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности файла (ответ на запрос).
 */
public class FileResponse {
  /**
   * Идентификатор файла.
   *
   * @return id Идентификатор файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Название файла.
   *
   * @return name Название файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Дата создания файла.
   *
   * @return dateCreated Дата создания файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreated;

  /**
   * Формат файла.
   *
   * @return format Формат файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter File.Format format;

  /**
   * Путь файла.
   *
   * @return urlFile Путь файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String urlFile;

  /**
   * Создает экземпляр класса {@Link FileResponse}.
   *
   * @param file Сущность файла.
   */
  public FileResponse(File file) {
    this.id = file.getId().getValue();
    this.name = file.getName();
    this.dateCreated = file.getDateCreated();
    this.format = file.getFormat();
    this.urlFile = getUrlFile();
  }
}
