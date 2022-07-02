package com.htc.application.dto.file;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.domain.entities.files.File;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * представление файла.
 */
public class FileDto {

  /**
   * Индентификатор файла.
   *
   * @return Индентификатор файла.
   */
  private final @Getter int id;
  /**
   * Имя файла.
   *
   * @return Имя файла.
   */
  private final @Getter String name;
  /**
   * Дата загрузки файла.
   *
   * @return Дата загрузки файла.
   */
  private final @Getter LocalDateTime dateCreated;
  /**
   * Формат файла.
   *
   * @return Формат файла.
   */
  private final @Getter String format;
  /**
   * URL файла.
   *
   * @return URL файла.
   */
  private final @Getter String url;

  /**
   * Создаёт экземпляр класса {@link FileDto}.
   *
   * @param file Файл.
   */
  public FileDto(File file) {
    this.id = file.getId();
    this.name = file.getName();
    this.dateCreated = file.getDateCreated();
    this.format = file.getFormat().name();
    this.url = file.getUrl();
  }
}