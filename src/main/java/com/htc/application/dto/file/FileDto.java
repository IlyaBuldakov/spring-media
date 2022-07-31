package com.htc.application.dto.file;

import com.htc.domain.entities.File;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * представление файла.
 */
public class FileDto {

  /**
   * Идентификатор файла.
   *
   * @return Идентификатор файла.
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
    this.id = file.id().getValue();
    this.name = file.name().getValue();
    this.dateCreated = file.dateCreated();
    this.format = file.format().name();
    this.url = file.url().getValue();
  }
}