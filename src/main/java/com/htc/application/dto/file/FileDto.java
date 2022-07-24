package com.htc.application.dto.file;

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
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;
  /**
   * Имя файла.
   *
   * @return Имя файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;
  /**
   * Дата загрузки файла.
   *
   * @return Дата загрузки файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreated;
  /**
   * Формат файла.
   *
   * @return Формат файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String format;
  /**
   * URL файла.
   *
   * @return URL файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String url;

  /**
   * Создаёт экземпляр класса {@link FileDto}.
   *
   * @param file Файл.
   */
  public FileDto(File file) {
    this.id = file.getId().getValue();
    this.name = file.getName().getValue();
    this.dateCreated = file.getDateCreated();
    this.format = file.getFormat().name();
    this.url = file.getUrl().getValue();
  }
}